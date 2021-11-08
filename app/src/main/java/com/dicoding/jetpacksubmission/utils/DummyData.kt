package com.dicoding.jetpacksubmission.utils

import com.dicoding.jetpacksubmission.data.db.model.MovieEntity
import com.dicoding.jetpacksubmission.data.db.model.TvShowEntity
import com.dicoding.jetpacksubmission.data.model.response.Movie
import com.dicoding.jetpacksubmission.data.model.response.TvShow

object DummyData {

    fun generateDummyMovieResponses(): List<Movie> = listOf(
        Movie(
            id = 337404,
            title = "Cruella",
            releaseDate = "2021-05-26",
            posterPath = "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
            overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            voteAverage = 8.6
        ),
        Movie(
            id = 423108,
            title = "The Conjuring: The Devil Made Me Do It",
            releaseDate = "2021-05-25",
            posterPath = "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
            overview = "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
            voteAverage = 8.1
        ),
        Movie(
            id = 637649,
            title = "Wrath of Man",
            releaseDate = "2021-04-22",
            posterPath = "/M7SUK85sKjaStg4TKhlAVyGlz3.jpg",
            overview = "A cold and mysterious new security guard for a Los Angeles cash truck company surprises his co-workers when he unleashes precision skills during a heist. The crew is left wondering who he is and where he came from. Soon, the marksman's ultimate motive becomes clear as he takes dramatic and irrevocable steps to settle a score.",
            voteAverage = 7.8
        ),
        Movie(
            id = 520763,
            title = "A Quiet Place Part II",
            releaseDate = "2021-05-21",
            posterPath = "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
            overview = "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
            voteAverage = 7.3
        ),
        Movie(
            id = 503736,
            title = "Army of the Dead",
            releaseDate = "2021-05-14",
            posterPath = "/z8CExJekGrEThbpMXAmCFvvgoJR.jpg",
            overview = "Following a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble: venturing into the quarantine zone to pull off the greatest heist ever attempted.",
            voteAverage = 6.5
        ),
        Movie(
            id = 460465,
            overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            posterPath = "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
            releaseDate = "2021-04-07",
            title = "Mortal Kombat",
            voteAverage = 7.5
        ),
        Movie(
            id = 632357,
            overview = "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
            posterPath = "/bShgiEQoPnWdw4LBrYT5u18JF34.jpg",
            releaseDate = "2021-03-31",
            title = "The Unholy",
            voteAverage = 7.0
        ),
        Movie(
            id = 817451,
            overview = "Jack Halsey takes his wife, their adult kids, and a friend for a dream vacation in Kenya. But as they venture off alone into a wilderness park, their safari van is flipped over by an angry rhino, leaving them injured and desperate. Then, as two of them go in search of rescue, a bloody, vicious encounter with a leopard and a clan of hyenas incites a desperate fight for survival.",
            posterPath = "/ccsSqbpEqr2KK9eMvoeF8ERFCd5.jpg",
            releaseDate = "2021-05-27",
            title = "Endangered Species",
            voteAverage = 6.7
        ),
        Movie(
            id = 399566,
            overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            posterPath = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            releaseDate = "2021-03-24",
            title = "Godzilla vs. Kong",
            voteAverage = 8.0
        ),
        Movie(
            id = 602734,
            overview = "Working in the shadow of an esteemed police veteran, brash Detective Ezekiel “Zeke” Banks and his rookie partner take charge of a grisly investigation into murders that are eerily reminiscent of the city’s gruesome past.  Unwittingly entrapped in a deepening mystery, Zeke finds himself at the center of the killer’s morbid game.",
            posterPath = "/lcyKve7nXRFgRyms9M1bndNkKOx.jpg",
            releaseDate = "2021-05-12",
            title = "Spiral: From the Book of Saw",
            voteAverage = 6.4
        ),
        Movie(
            id = 522406,
            overview = "Frederick Fitzell is living his best life—until he starts having horrific visions of Cindy, a girl who vanished in high school. After reaching out to old friends with whom he used to take a mystery drug called Mercury, Fredrick realizes the only way to stop the visions lies deep within his own memories, so he embarks on a terrifying mental odyssey to learn the truth.",
            posterPath = "/nc7szo7ChOknEk8qCkABJyNgl5y.jpg",
            releaseDate = "2021-06-03",
            title = "Flashback",
            voteAverage = 6.8
        ),
        Movie(
            id = 615457,
            overview = "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            posterPath = "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
            releaseDate = "2021-03-26",
            title = "Nobody",
            voteAverage = 8.4
        ),
        Movie(
            id = 508943,
            overview = "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.",
            posterPath = "/jTswp6KyDYKtvC52GbHagrZbGvD.jpg",
            releaseDate = "2021-06-17",
            title = "Luca",
            voteAverage = 8.3
        ),
        Movie(
            id = 578701,
            overview = "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.  Download : https://hd.movietv.biz/movie/578701/those-who-wish-me-dead.html",
            posterPath = "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
            releaseDate = "2021-05-05",
            title = "Those Who Wish Me Dead",
            voteAverage = 7.0
        ),
        Movie(
            id = 635302,
            overview = "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            posterPath = "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            releaseDate = "2020-10-16",
            title = "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
            voteAverage = 8.4
        ),
        Movie(
            id = 804435,
            overview = "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            posterPath = "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            releaseDate = "2021-04-16",
            title = "Vanquish",
            voteAverage = 6.0
        ),
        Movie(
            id = 385128,
            overview = "Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
            posterPath = "/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg",
            releaseDate = "2021-05-19",
            title = "F9",
            voteAverage = 8.0
        ),
        Movie(
            id = 634528,
            overview = "Jim Hanson’s quiet life is suddenly disturbed by two people crossing the US/Mexico border – a woman and her young son – desperate to flee a Mexican cartel. After a shootout leaves the mother dead, Jim becomes the boy’s reluctant defender. He embraces his role as Miguel’s protector and will stop at nothing to get him to safety, as they go on the run from the relentless assassins.",
            posterPath = "/6vcDalR50RWa309vBH1NLmG2rjQ.jpg",
            releaseDate = "2021-01-15",
            title = "The Marksman",
            voteAverage = 7.4
        ),
        Movie(
            id = 458576,
            overview = "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
            posterPath = "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
            releaseDate = "2020-12-03",
            title = "Monster Hunter",
            voteAverage = 7.0
        ),
        Movie(
            id = 580532,
            overview = "Three stories about the world of opioids collide: a drug trafficker arranges a multi-cartel Fentanyl smuggling operation between Canada and the U.S., an architect recovering from an OxyContin addiction tracks down the truth behind her son's involvement with narcotics, and a university professor battles unexpected revelations about his research employer, a drug company with deep government influence bringing a new \"non-addictive\" painkiller to market.",
            posterPath = "/ao4JajLcPITivfstlzENge3MPkq.jpg",
            releaseDate = "2021-02-26",
            title = "Crisis",
            voteAverage = 6.6
        )
    )


    fun generateDummyTvShowResponses(): List<TvShow> = listOf(
        TvShow(
            id = 84958,
            releaseDate = "2021-06-09",
            title = "Loki",
            overview = "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
            posterPath = "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
            voteAverage = 8.1
        ),
        TvShow(
            id = 60735,
            releaseDate = "2014-10-07",
            title = " The Flash",
            overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            posterPath = "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
            voteAverage = 7.7
        ),
        TvShow(
            id = 95057,
            releaseDate = "2021-02-23",
            title = " Superman & Lois ",
            overview = "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
            posterPath = "/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
            voteAverage = 8.3
        ),
        TvShow(
            id = 60625,
            releaseDate = "2013-12-02",
            title = " Rick and Morty ",
            overview = "Rick is a mentally-unbalanced but scientifically-gifted old man who has recently reconnected with his family. He spends most of his time involving his young grandson Morty in dangerous, outlandish adventures throughout space and alternate universes. Compounded with Morty's already unstable family life, these events cause Morty much distress at home and school.",
            posterPath = "/8kOWDBK6XlPUzckuHDo3wwVRFwt.jpg",
            voteAverage = 8.7
        ),
        TvShow(
            id = 79460,
            releaseDate = "2018-10-25",
            title = " Legacies ",
            overview = "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
            posterPath = "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
            voteAverage = 8.6
        ),
        TvShow(
            id = 105971,
            releaseDate = "2021-05-04",
            title = " The Bad Batch ",
            overview = "The 'Bad Batch' of elite and experimental clones make their way through an ever-changing galaxy in the immediate aftermath of the Clone Wars.",
            posterPath = "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
            voteAverage = 8.7
        ),
        TvShow(
            id = 58142,
            releaseDate = "2021-06-04",
            title = "Paraíso",
            overview = "Paraiso is a 2012 Philippine daytime television drama that aired on ABS-CBN from November 5, 2012 to April 5, 2013, replacing the PHR's high-rating series entitled Pintada which was starred Denise Laurel and Martin del Rosario.\n\nParaiso is the 17th installment of the Precious Hearts Romances series, and is topbilled by Jessy Mendiola and Matteo Guidicelli.",
            posterPath = "/6KkM6gLFloyybv6qFZs6t5taWws.jpg",
            voteAverage = 0.0
        ),
        TvShow(
            id = 119243,
            releaseDate = "2021-06-17",
            title = "iCarly",
            overview = "Ten years after signing off of one of TV's most iconic shows, Carly, Spencer, and Freddie are back, navigating the next chapter of their lives, facing the uncertainties of life in their twenties.",
            posterPath = "/s5k4GqTUGXeUdScNrjpYfiQLKHI.jpg",
            voteAverage = 8.5
        ),
        TvShow(
            id = 65820,
            releaseDate = "2016-09-23",
            title = "Van Helsing",
            overview = "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
            posterPath = "/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
            voteAverage = 7.0
        ),
        TvShow(
            id = 105009,
            releaseDate = "2021-04-11",
            title = "Tokyo Revengers",
            overview = "Takemichi Hanagaki is a freelancer that’s reached the absolute pits of despair in his life. He finds out that the only girlfriend he ever had, in middle school, Hinata Tachibana, had been killed by the ruthless Tokyo Manji Gang. The day after hearing about her death, he’s standing on the station platform and ends up being pushed over onto the tracks by a herd of people. He closes his eyes thinking he’s about to die, but when he opens his eyes back up, he somehow had gone back in time 12 years. Now that he’s back living the best days of his life, Takemichi decides to get revenge on his life.",
            posterPath = "/qSEKyf0fWhrCEQ3LTwLqe41eSvR.jpg",
            voteAverage = 8.0
        ),
        TvShow(
            id = 46952,
            releaseDate = "2013-09-23",
            title = "The Blacklist",
            overview = "Raymond \"Red\" Reddington, one of the FBI's most wanted fugitives, surrenders in person at FBI Headquarters in Washington, D.C. He claims that he and the FBI have the same interests: bringing down dangerous criminals and terrorists. In the last two decades, he's made a list of criminals and terrorists that matter the most but the FBI cannot find because it does not know they exist. Reddington calls this \"The Blacklist\". Reddington will co-operate, but insists that he will speak only to Elizabeth Keen, a rookie FBI profiler.",
            posterPath = "/htJzeRcYI2ewMm4PTrg98UMXShe.jpg",
            voteAverage = 7.4
        ),
        TvShow(
            id = 104877,
            releaseDate = "2020-07-08",
            title = "You Knock on My Door",
            overview = "Connecting all her hopes in her life to her education, Eda comes face to face with Serkan Bolat, who unknowingly cuts her scholarship abroad and caused her to remain a high school graduate. Serkan Bolat offers to give Eda her scholarship back if she pretends to be engaged to him for two months. Eda first rejects the offer due to her hatred for him, but when circumstances change, she has no choice but to accept the offer. While pretending to be engaged, Serkan and Eda begin to have a passionate, challenging relationship that will make you forget all they know right, because love is hard. And that's why it's amazing!!",
            posterPath = "/nzBenFJ2D2MCWTwTt2uc4BGWLtA.jpg",
            voteAverage = 8.1
        ),
        TvShow(
            id = 85801,
            releaseDate = "2019-11-12",
            title = "High School Musical: The Musical: The Series",
            overview = "A group of East High students countdown to the opening night of their school’s first-ever production of “High School Musical.” Showmances blossom; friendships are tested while new ones are made; rivalries flare and lives are changed forever as these young people discover the transformative power that only a high school drama club can provide.",
            posterPath = "/9S3BS5oS9a8sNiTWuZi9aveE4bR.jpg",
            voteAverage = 8.1
        ),
        TvShow(
            id = 89247,
            releaseDate = "2019-10-06",
            title = "Batwoman",
            overview = "Kate Kane, armed with a passion for social justice and a flair for speaking her mind, soars onto the streets of Gotham as Batwoman, an out lesbian and highly trained street fighter primed to snuff out the failing city's criminal resurgence. But don't call her a hero yet. In a city desperate for a savior, Kate must overcome her own demons before embracing the call to be Gotham's symbol of hope",
            posterPath = "/xjyEpcuDbB1jy0ehNQMBiO8KOdr.jpg",
            voteAverage = 7.3
        ),
        TvShow(
            id = 62643,
            releaseDate = "2016-01-21",
            title = "DC's Legends of Tomorrow",
            overview = "When heroes alone are not enough ... the world needs legends. Having seen the future, one he will desperately try to prevent from happening, time-traveling rogue Rip Hunter is tasked with assembling a disparate group of both heroes and villains to confront an unstoppable threat — one in which not only is the planet at stake, but all of time itself. Can this ragtag team defeat an immortal threat unlike anything they have ever known?",
            posterPath = "/uzQpQGISbtxc047IOssFo5AiDwU.jpg",
            voteAverage = 7.2
        ),
        TvShow(
            id = 79611,
            releaseDate = "2018-10-14",
            title = "Charmed",
            overview = "Set in the fictional college town of Hilltowne, Charmed follows the lives of three sisters, Macy, Mel and Maggie Vera who, after the tragic death of their mother, discover they are three of the most powerful witches of all time.",
            posterPath = "/jyArBuSbEnSoQinAZBU6pZmmL6M.jpg",
            voteAverage = 7.3
        ),
        TvShow(
            id = 66084,
            releaseDate = "2014-09-27",
            title = "Acapulco Shore",
            overview = "A reality-based look at the vapid lives of several Mexican 20-somethings and their respective friends and/or hook-ups during their stay in Acapulco for a Summer vacation.",
            posterPath = "/7sfy3nq1CjeoOgwg3N9hO8vLZSH.jpg",
            voteAverage = 7.9
        ),
        TvShow(
            id = 32415,
            releaseDate = "2010-11-08",
            title = "Conan",
            overview = "A late night television talk show hosted by  Conan O'Brien.",
            posterPath = "/oQxrvUhP3ycwnlxIrIMQ9Z3kleq.jpg",
            voteAverage = 7.2
        ),
        TvShow(
            id = 1433,
            releaseDate = "2005-02-06",
            title = "American Dad!",
            overview = "The series focuses on an eccentric motley crew that is the Smith family and their three housemates: Father, husband, and breadwinner Stan Smith; his better half housewife, Francine Smith; their college-aged daughter, Hayley Smith; and their high-school-aged son, Steve Smith. Outside of the Smith family, there are three additional main characters, including Hayley's boyfriend turned husband, Jeff Fischer; the family's man-in -a-goldfish-body pet, Klaus; and most notably the family's zany alien, Roger, who is \"full of masquerades, brazenness, and shocking antics.\"",
            posterPath = "/aC1q422YhQR7k82GB8gW4KoD91p.jpg",
            voteAverage = 6.8
        ),
        TvShow(
            id = 116386,
            releaseDate = "2021-05-10",
            title = "Doom at Your Service",
            overview = "Dong-kyung has been working hard ever since her parents passed away. Her life seems to get stable after working as an web novel editor for 6 years, but one day she gets diagnosed with a brain cancer. She blames her unlucky life and wishes to curse everything to disappear, which unintentionally calls Myul Mang, a messenger between humans and gods, to appear. He says that he can grant her wishes. As her last hope, she makes a contract with Myul Mang for hundred days to live as how she wants, risking her everything.",
            posterPath = "/o1aus1uSJH0S4FDUlBRCSfKEKuW.jpg",
            voteAverage = 9.2
        )
    )

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        generateDummyMovieResponses().map {
            movies.add(it.toEntity())
        }

        return movies
    }

    fun generateSearchDummyMovies(query: String): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        generateDummyMovieResponses().map {
            if (it.title?.contains(query) == true) {
                movies.add(it.toEntity())
            }
        }

        return movies
    }

    fun generateSearchDummyMoviesResponse(query: String): List<Movie> {
        val movies = ArrayList<Movie>()

        generateDummyMovieResponses().map {
            if (it.title?.contains(query) == true) {
                movies.add(it)
            }
        }

        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShows = ArrayList<TvShowEntity>()

        generateDummyTvShowResponses().map {
            tvShows.add(it.toEntity())
        }

        return tvShows
    }

    fun generateSearchDummyTvShows(query: String): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        generateDummyTvShowResponses().map {
            if (it.title?.contains(query) == true) {
                tvShow.add(it.toEntity())
            }
        }

        return tvShow
    }

    fun generateSearchDummyTvShowsResponse(query: String): List<TvShow> {
        val tvShows = ArrayList<TvShow>()

        generateDummyTvShowResponses().map {
            if (it.title?.contains(query) == true) {
                tvShows.add(it)
            }
        }

        return tvShows
    }
}