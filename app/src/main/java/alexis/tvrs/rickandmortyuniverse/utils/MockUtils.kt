package alexis.tvrs.rickandmortyuniverse.utils

import alexis.tvrs.rickandmortyuniverse.wiki.data.models.MarketplaceOffer
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyCharacter
import alexis.tvrs.rickandmortyuniverse.wiki.data.models.RickAndMortyLocation

object MockUtils {
    val dummyCharacter = RickAndMortyCharacter(1,"TestOffer","Test","Offer","Offer","Test",
            RickAndMortyLocation(1,"Lcoation","null","null",ArrayList(),"null","null"),
            RickAndMortyLocation(1,"Lcoation","null","null",ArrayList(),"null","null"),
            "",ArrayList(),"")

    val marketplaceOffers = listOf(MarketplaceOffer("sg4sd68gsa","https://rickandmortyapi.com/api/character/avatar/1.jpeg","Rick Sanchez", 25, "Ufs57s6sgsh"),
            MarketplaceOffer("sg4sdkggsa","https://rickandmortyapi.com/api/character/avatar/38.jpeg","Rick Sanchez", 86, "Ufs57s6svclozqsfgsh"),
            MarketplaceOffer("sg4sdadgs68gsa","https://rickandmortyapi.com/api/character/avatar/84.jpeg","Rick Sanchez", 69, "Ufs57s4556sgsh"),
            MarketplaceOffer("sg4sddfbs68gsa","https://rickandmortyapi.com/api/character/avatar/127.jpeg","Rick Sanchez", 42, "Ufs57536487s6sgsh")
    )
}