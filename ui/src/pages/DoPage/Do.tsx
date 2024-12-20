import React, {useEffect, useState} from 'react';
import axios from 'axios';
import PageHero from "../../components/PageHero";
import PageIntro from "../../components/PageIntro";
import Dropdown from "../../components/Dropdown";
import ListingItem from "../../components/ListingItem";
import Pagination from "../../components/Pagination";

interface Listing {
    id: number
    imageUrl: string;
    imageAltText: string;
    name: string;
    description: string;
    phone: string;
    cost: string;

}


function Do() {
    const apiURL = process.env.REACT_APP_API_URL;
    const [doType, setDoType] = useState("all");
    const [region, setRegion] = useState("all");
    const [listings, setListings] = useState<Listing[]>([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const [pageSize, setPageSize] = useState<number>();


    useEffect(() => {
        axios.get(`${apiURL}/api/things-to-do/list?doType=${doType}&region=${region}&page=${page}`)
            .then(response => {
                setListings(response.data.content);
                setPageSize(response.data.size);
                setTotalPages(response.data.totalPages);

            })
            .catch(error => {
                console.log("Error getting things to do:", error);
            });
    },[apiURL, doType, region, page, pageSize]);

    const handlePrevious = () => {
        setPage(prevPage => prevPage > 1 ? prevPage -1 :prevPage);
    };

    const handleNext = () => {
        setPage(prevPage => prevPage < totalPages ? prevPage + 1 : prevPage);
    };
    
    return (
      <div>
        <PageHero
          imageSrc="https://ernestocapstoneimages.blob.core.windows.net/images/do-hero.jpg"
          altText="two people kayaking"
        />
        <div className="container">
          <PageIntro
            heading="things to do"
            introText="Most people visit Taniti to enjoy the beaches, explore the rainforest, and to visit the volcano. However, there are
    other things to do, including visiting a local history museum, going on chartered fishing tours, snorkeling, zip-lining
    in the rainforest, dancing at a new dance club, seeing a movie, taking taking helicopter rides, playing at an arcade,
    visiting art galleries, and bowling. Also, a nine-hole golf course should be operational by next year. Many of these
    activities are located in Merriton Landing, which is a rapidly developing area on the north side of Yellow Leaf Bay."
          />
          <form className="row ps-5 mt-5">
            <Dropdown
              apiEndpoint="/api/things-to-do/type"
              label="By Type"
              id="doType"
              onChange={(newDoType) => setDoType(newDoType)}
            />
            <Dropdown
              apiEndpoint="/api/things-to-do/region"
              label="Region"
              id="region"
              onChange={(newRegion) => setRegion(newRegion)}
            />
          </form>
          {listings.map((listing) => (
            <ListingItem
              key={listing.id}
              imageUrl={listing.imageUrl}
              imageAltText={listing.imageAltText}
              name={listing.name}
              description={listing.description}
              phone={listing.phone}
              cost={listing.cost}
            />
          ))}
          {totalPages > 1 && (
            <Pagination
              page={page}
              totalPages={totalPages}
              handlePrevious={handlePrevious}
              handleNext={handleNext}
            />
          )}
        </div>
      </div>
    );
}

export default Do;