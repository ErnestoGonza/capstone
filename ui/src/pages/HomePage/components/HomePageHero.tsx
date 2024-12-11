import React from 'react';

function HomePageHero () {
    return (
      <div>
        <img
          src="https://ernestocapstoneimages.blob.core.windows.net/images/taniti-hero.jpg"
          className="d-block w-100 img-hero"
          alt="taniti tropical island"
        />
        <h2 className="tagline">
          <em>Your island escape awaits...</em>
        </h2>
        <div className="hero-img-container">
          <img
            src="https://ernestocapstoneimages.blob.core.windows.net/images/taniti-logo-outline.jpg"
            alt="taniti logo"
            className="img-logo-hero"
          />
        </div>
      </div>
    );
}

export default HomePageHero;