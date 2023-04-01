
## class diagram

[![](https://mermaid.ink/img/pako:eNqtk8FOwzAMhl8l8nF0E1xz5oK0IcTYBeVitd6I1iRVkjJV094dpw0TW0e50Evc35_j32lzhNJVBBLKGkN41LjzaJQV_GwCeaHgQYGYzecc3S8WM355pU9NByHFwetIYRpe6j0xWvOSyVw-sD2agpVjlUHfZzPaF_8C5h0HsDc_eDgOihB3upJiHb22u7PUMvGMhkYJT1jfTKSKt67hxCZHF6kX7nu46nP66SlP-6erkWjSnGOfaPdSPNl4VkpnDNk4IiuMXL50N131B_tfniY7DZ9rspW9de47sn6sdoT-cvzQWdcEHcautKcyuqshTlCAIW9QV_zP97YUxA8ypEByWNEW2zoqUDah2Ea37mwJMvqWCmibNGy-JSC3WAdWqdLcaJXvUVoKaNC-O_fNnL4AsUYGgA?type=png)](https://mermaid.live/edit#pako:eNqtk8FOwzAMhl8l8nF0E1xz5oK0IcTYBeVitd6I1iRVkjJV094dpw0TW0e50Evc35_j32lzhNJVBBLKGkN41LjzaJQV_GwCeaHgQYGYzecc3S8WM355pU9NByHFwetIYRpe6j0xWvOSyVw-sD2agpVjlUHfZzPaF_8C5h0HsDc_eDgOihB3upJiHb22u7PUMvGMhkYJT1jfTKSKt67hxCZHF6kX7nu46nP66SlP-6erkWjSnGOfaPdSPNl4VkpnDNk4IiuMXL50N131B_tfniY7DZ9rspW9de47sn6sdoT-cvzQWdcEHcautKcyuqshTlCAIW9QV_zP97YUxA8ypEByWNEW2zoqUDah2Ea37mwJMvqWCmibNGy-JSC3WAdWqdLcaJXvUVoKaNC-O_fNnL4AsUYGgA)

```
classDiagram
    User "1" *-- "0..*" Review : writes
    User "1" *-- "0..*" Like : likes
    Review "1" -- "1" Movie : reviews
    Like "1" -- "1" Movie : likes

    class User {
      +id: String
      +userName: String
      +realName: String
      +userType: UserType
      +userPasswd: String
    }
    class Review {
      +id: String
      +user: String
      +movie: String
      +rank: Int
      +comment: String
      +date: Long
    }
    class Like {
      +id: String
      +user: String
      +movie: String
      +date: Long
    }
    class Movie {
      +id: String
      +name: String
      +genre: String
      +year: Int
      +synopsis: String
      +director: String
    }
```


## register user

[![](https://mermaid.ink/img/pako:eNqNUr1OwzAQfpWTJ5DSF8hQCVEGJJgqGFCWw76kFokdzrZKVPXducRNFVqGejr5vp_7OyjtDalSBfpO5DRtLDaMXeVAXo8crbY9ughvgfj699lF4ho1XaderTEtveDwH2-DET8xCC3nRvXVen2WK-FpDAPEHVkGI3C4c9hRAUmgOeoxhL1nc581zmQRWpiX8E5s62ESychFVrBzLSU87kh_TQYZh23uG-jHhhjy5_hmyurSakJjy4RmuGItbf82u935PRCzZ-goBGxO86Q2UNY0ngI4H7PoDZU8GLPo5Ab7kLQW7zq1wNSICWO03l0U5MzlsEVqrPCkoj0zhd47Y10zc1WhOuIOrZFLO4wClZLFdlSpUkJDNaY2VqpyR4Fiin47OK3KyIkKlXrZ3HyYqqxRhlIoMjZ6fs3XOx1xoeS0PryfMcdfm_75Vw?type=png)](https://mermaid.live/edit#pako:eNqNUr1OwzAQfpWTJ5DSF8hQCVEGJJgqGFCWw76kFokdzrZKVPXducRNFVqGejr5vp_7OyjtDalSBfpO5DRtLDaMXeVAXo8crbY9ughvgfj699lF4ho1XaderTEtveDwH2-DET8xCC3nRvXVen2WK-FpDAPEHVkGI3C4c9hRAUmgOeoxhL1nc581zmQRWpiX8E5s62ESychFVrBzLSU87kh_TQYZh23uG-jHhhjy5_hmyurSakJjy4RmuGItbf82u935PRCzZ-goBGxO86Q2UNY0ngI4H7PoDZU8GLPo5Ab7kLQW7zq1wNSICWO03l0U5MzlsEVqrPCkoj0zhd47Y10zc1WhOuIOrZFLO4wClZLFdlSpUkJDNaY2VqpyR4Fiin47OK3KyIkKlXrZ3HyYqqxRhlIoMjZ6fs3XOx1xoeS0PryfMcdfm_75Vw)

```
sequenceDiagram
    participant User
    participant Interface
    participant MiddleLayer
    participant Database

    User->>Interface: Enters their data (name, username, password)
    Interface->>MiddleLayer: Verify data
    MiddleLayer->>Database: Check user
    alt User exists
        Database-->>MiddleLayer: User already exists
        MiddleLayer-->>Interface: Show error message
    else User does not exist
        Database-->>MiddleLayer: Add user
        MiddleLayer-->>Interface: Show successful registration message
    end
    Interface-->>User: Show corresponding message

```


## authentication diagram

[![](https://mermaid.ink/img/pako:eNqllMFKxDAQhl9lyElhfYEevKiooCCIHqSXMZluB9NkzaTqIr67U9PuVl0FsaeQmf_Lnz9NXo2NjkxlhB57CpaOGZcJuzqAfitMmS2vMGS4EUrfZ89DptSgpe-lS3bO0wWud-mOMeM9ispKbaAfHB5ucBWcDEPwcckBbCJHITN6gb1eWwN2tFCgyHNMbr8wNmIFzRav4JYSN-s5pQhmTSqZLFVw1JJ9AG5gWArohSWPCvQlCHCRBELMpVqKwzdBDr56-FU1N_I5hes2PgOHJ_TsxjA6EsHlGDh5ocKe2_zVySnlsrEpvt0-vqjOUFqgwRi5vymPYqcnT9AqgWYGhyyvRhCwTLvcNvwvmU068zV2rPBjTieiBc-6bVEux_AnZ9Jbq7Km9z-aC6OTzWD7AytwONSRZWNKJKsYHIflFmQWpqPUITu9v68Doja5pY5qU-nQUYO9z7Wpw5u2Yp_j9TpYU-XU08L0K4d5uu6mavRW6Cw5zjFdljfh42lYGL2wdzFOPW_vtb9oAw?type=png)](https://mermaid.live/edit#pako:eNqllMFKxDAQhl9lyElhfYEevKiooCCIHqSXMZluB9NkzaTqIr67U9PuVl0FsaeQmf_Lnz9NXo2NjkxlhB57CpaOGZcJuzqAfitMmS2vMGS4EUrfZ89DptSgpe-lS3bO0wWud-mOMeM9ispKbaAfHB5ucBWcDEPwcckBbCJHITN6gb1eWwN2tFCgyHNMbr8wNmIFzRav4JYSN-s5pQhmTSqZLFVw1JJ9AG5gWArohSWPCvQlCHCRBELMpVqKwzdBDr56-FU1N_I5hes2PgOHJ_TsxjA6EsHlGDh5ocKe2_zVySnlsrEpvt0-vqjOUFqgwRi5vymPYqcnT9AqgWYGhyyvRhCwTLvcNvwvmU068zV2rPBjTieiBc-6bVEux_AnZ9Jbq7Km9z-aC6OTzWD7AytwONSRZWNKJKsYHIflFmQWpqPUITu9v68Doja5pY5qU-nQUYO9z7Wpw5u2Yp_j9TpYU-XU08L0K4d5uu6mavRW6Cw5zjFdljfh42lYGL2wdzFOPW_vtb9oAw)

```
sequenceDiagram
    participant User
    participant Interface
    participant MiddleLayer
    participant Database

    User->>Interface: Enter login credentials (username, password)
    Interface->>MiddleLayer: Verify credentials
    MiddleLayer->>Database: Check if user exists
    alt User does not exist
        Database-->>MiddleLayer: User does not exist
        MiddleLayer-->>Interface: Show invalid login message
    else User exists
        Database-->>MiddleLayer: Get user password
        MiddleLayer->>MiddleLayer: Hash entered password
        MiddleLayer->>MiddleLayer: Compare hashes
        alt Password is invalid
            MiddleLayer-->>Interface: Show invalid login message
        else Password is valid
            Database-->>MiddleLayer: Establish session
            MiddleLayer-->>Interface: Show successful login message
        end
    end
    Interface-->>User: Show corresponding message

```

## add review

[![](https://mermaid.ink/img/pako:eNqdVE1P3EAM_SvWnEBKkXrgkgOnrao9cCiolYpycRLvMiLxpDMeYIX47_XkS1k2AkROo_j5PfvZ8oupXE0mN4H-ReKKNhb3HtuCQb8OvdjKdsgCvwP5079bFvI7rOg0tEHBEsNK5JZCsI4LHkKJ-dvV1UyVw4_0hNY9WoLtJgOP_JABcg2Va1tiGRLnDM0eOXP4SQJV9F5REOeax_CxzA1J9LyCXhJPbeTwK5I_jFWVBy1sAE-AVe4e_TFpkgZPin0KsHN-mfcu_zLxVGaB_4ONrVEILHdRApz1CpO1o63nAwU2ovyJU5-esD4APdsgo8KxisqkAeawsaFrUKHepwbUcNwTnP11cWYZCqUa5N6GocdRkppAcN1by07Ugsj1V9TeUCzZt_yYPDjuOAPN1j97-oraB5QXcKMBaGMQKAkQOLalzqskeSJi-N6v9OXFcZUqKKNTazUtl2cEsw7qbcK8NatrE2JVaRNTJ5_qfTWH0phMZlryLdpaD8lLChRG7qmlwuT6rGmHsZHCFPyqUIzibg9cmVx8pMzELu3leHdMvkO1ITNUW3H-ejhO_Y3KjB6PO-cmzOt_a_mcCA?type=png)](https://mermaid.live/edit#pako:eNqdVE1P3EAM_SvWnEBKkXrgkgOnrao9cCiolYpycRLvMiLxpDMeYIX47_XkS1k2AkROo_j5PfvZ8oupXE0mN4H-ReKKNhb3HtuCQb8OvdjKdsgCvwP5079bFvI7rOg0tEHBEsNK5JZCsI4LHkKJ-dvV1UyVw4_0hNY9WoLtJgOP_JABcg2Va1tiGRLnDM0eOXP4SQJV9F5REOeax_CxzA1J9LyCXhJPbeTwK5I_jFWVBy1sAE-AVe4e_TFpkgZPin0KsHN-mfcu_zLxVGaB_4ONrVEILHdRApz1CpO1o63nAwU2ovyJU5-esD4APdsgo8KxisqkAeawsaFrUKHepwbUcNwTnP11cWYZCqUa5N6GocdRkppAcN1by07Ugsj1V9TeUCzZt_yYPDjuOAPN1j97-oraB5QXcKMBaGMQKAkQOLalzqskeSJi-N6v9OXFcZUqKKNTazUtl2cEsw7qbcK8NatrE2JVaRNTJ5_qfTWH0phMZlryLdpaD8lLChRG7qmlwuT6rGmHsZHCFPyqUIzibg9cmVx8pMzELu3leHdMvkO1ITNUW3H-ejhO_Y3KjB6PO-cmzOt_a_mcCA)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: Enter movie ID, rank, and comment
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query movie by ID
    Database->>Interface: Return movie
    Interface->>Database: Query user reviews for movie
    Database->>Interface: Return user reviews
    Interface->>Interface: Validate inputs (movie, rank, comment)
    alt Review already exists
        Interface-->>User: Display error message (You already reviewed this movie)
    else Movie not found
        Interface-->>User: Display error message (Movie not found)
    else Invalid rank, comment, or range
        Interface-->>User: Display error message (Invalid rank, comment, or range. Rank must be a number between 1 and 5.)
    else Insert review
        Interface->>Database: Insert new review
        Database-->>Interface: Return success message
        Interface-->>User: Display success message
    end

```

## add like

[![](https://mermaid.ink/img/pako:eNqdk81OwzAQhF_F8gmk8AI-9BSEKtEDVBxAuWydbWuR2MFeA1HVd2fzS9pGgOjJzYy_nYyyB6ldjlLJzAZ8i2g1pgZ2HsrMCv5V4MloU4El8RTQXz5dWkK_BY2XUgoEGwgzyhpDMM5mtpMa8s1iMaKUuG2OonTvBsUy7VyjzNYeoMQdktDRe2RqHAP28inzESl6O-OegofMSjxE9HUfYVOPKQbDLLt1_w5tRovCvGIQW-ent36kf1_rzFCQuOe_fPAIeS3w0wTqxdMMzGtKViI1oSqArd43k7kn2KG4enZxpDQTckF7E7pk1x0Qi4Bi1dZhHXHwaPP_jDpDTOlL_gg9tQHmyNMie6vFjzP72OBshSFqzUGGNH_KP3sHm5eXiSzRl2By3qBDI2SS9lhiJhUfc9xCLCjj5TqyFSK5dW21VOQjJjJWOdCwblJtgStIJOaGnF91W9kuZyJ5Z16cGzzHL3bvQgE?type=png)](https://mermaid.live/edit#pako:eNqdk81OwzAQhF_F8gmk8AI-9BSEKtEDVBxAuWydbWuR2MFeA1HVd2fzS9pGgOjJzYy_nYyyB6ldjlLJzAZ8i2g1pgZ2HsrMCv5V4MloU4El8RTQXz5dWkK_BY2XUgoEGwgzyhpDMM5mtpMa8s1iMaKUuG2OonTvBsUy7VyjzNYeoMQdktDRe2RqHAP28inzESl6O-OegofMSjxE9HUfYVOPKQbDLLt1_w5tRovCvGIQW-ent36kf1_rzFCQuOe_fPAIeS3w0wTqxdMMzGtKViI1oSqArd43k7kn2KG4enZxpDQTckF7E7pk1x0Qi4Bi1dZhHXHwaPP_jDpDTOlL_gg9tQHmyNMie6vFjzP72OBshSFqzUGGNH_KP3sHm5eXiSzRl2By3qBDI2SS9lhiJhUfc9xCLCjj5TqyFSK5dW21VOQjJjJWOdCwblJtgStIJOaGnF91W9kuZyJ5Z16cGzzHL3bvQgE)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: Enter movie ID
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query movie by ID
    Database->>Interface: Return movie
    Interface->>Database: Query user likes for movie
    Database->>Interface: Return user likes
    alt Like already exists
        Interface-->>User: Display error message (You already liked this movie)
    else Movie not found
        Interface-->>User: Display error message (Movie not found)
    else Insert like
        Interface->>Database: Insert new like
        Database-->>Interface: Return success message
        Interface-->>User: Display success message
    end

```

## remove review

[![](https://mermaid.ink/img/pako:eNqdU8tugzAQ_BXLp0aiP-BDTlRVDjk0US8Vl41ZUktg07XdCkX59y4QIA-SSuW08jyYMexBapejVNLjV0SrMTWwJ6gyK_ipgYLRpgYbxLtHuj1d2YBUgMZbKIUAO_AzyBa9N85mtoda5-flcrRS4qUdReW-DYpV2rNGmKknAyVeMQgdiZBd4xjwBF96bjBEsjPsc-MhsxJvEak5Rdg1Y4qBMOvdsXselEGsO7F1QRQu2rwHLt94aZMaX5fQCCRyXJ9bwB7F05XPojfC0uPD7G1DQcjaHxbSebo_m5xpJ0XbadP7zZT6R7Frs8Xk1rXbIGfGmxz3P9oDwVh3tq-PWnOqIdqDVu3POhW6q8PhaniQiayQKjA579mhPc5k-MQKM6l4zLGAWIZMZvbIVIjBbRurpQoUMZGxziEMaylVAXwzicTcBEfrfne7FU4k79aHcwPn-AunbkgA?type=png)](https://mermaid.live/edit#pako:eNqdU8tugzAQ_BXLp0aiP-BDTlRVDjk0US8Vl41ZUktg07XdCkX59y4QIA-SSuW08jyYMexBapejVNLjV0SrMTWwJ6gyK_ipgYLRpgYbxLtHuj1d2YBUgMZbKIUAO_AzyBa9N85mtoda5-flcrRS4qUdReW-DYpV2rNGmKknAyVeMQgdiZBd4xjwBF96bjBEsjPsc-MhsxJvEak5Rdg1Y4qBMOvdsXselEGsO7F1QRQu2rwHLt94aZMaX5fQCCRyXJ9bwB7F05XPojfC0uPD7G1DQcjaHxbSebo_m5xpJ0XbadP7zZT6R7Frs8Xk1rXbIGfGmxz3P9oDwVh3tq-PWnOqIdqDVu3POhW6q8PhaniQiayQKjA579mhPc5k-MQKM6l4zLGAWIZMZvbIVIjBbRurpQoUMZGxziEMaylVAXwzicTcBEfrfne7FU4k79aHcwPn-AunbkgA)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: Enter movie ID
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query movie by ID
    Database->>Interface: Return movie
    alt Movie not found
        Interface-->>Interface: Display error message (Movie not found)
    else Interface->>Database: Query user review for movie
        Database->>Interface: Return user review
        alt Review not found
            Interface-->>Interface: Display error message (Review not found)
        else Remove review
            Interface->>Database: Remove review
            Database-->>Interface: Return success message
            Interface-->>User: Display success message
        end
    end

```

## remove like

[![](https://mermaid.ink/img/pako:eNqdk01qwzAQha8itGrAvYAXWbmUQLNoQjfFm4k8TkVtyZVGBRNy947_48ZJoV4NmjdP7xOek1Q2QxlLj18BjcJEw9FBmRrBXwWOtNIVGBJvHt316cYQuhwUXrcSIDiAX-js0XttTWq6VuP8uF6PVrF4akpR2m-NYpN0qrHN0t4gFs9IQgXnkF3DGLBvzz13SMGZBfWl8ZA5Fq8BXd1HONRjikGw6N2qOx0UJLbtsLEkchtM1jXmN85tEu2rAmqBzlnGZwo4onj45bPqjLDweDd7QygK_Yme59xluD9BptFpoCF64aMloH9Aza1Wk1fLtUOOi22EW7dcAt-Uj5SLmD4oxXmGUHd4ml90Qrk5h8OjcCEjWaIrQWe8XafmOJX0gSWmMuYywxxCQalMzZmlEMjua6NkTC5gJEOVAQ3LKOMc-FUiiZkm67bdxraLG0neqHdrB835B9zqQ1g?type=png)](https://mermaid.live/edit#pako:eNqdk01qwzAQha8itGrAvYAXWbmUQLNoQjfFm4k8TkVtyZVGBRNy947_48ZJoV4NmjdP7xOek1Q2QxlLj18BjcJEw9FBmRrBXwWOtNIVGBJvHt316cYQuhwUXrcSIDiAX-js0XttTWq6VuP8uF6PVrF4akpR2m-NYpN0qrHN0t4gFs9IQgXnkF3DGLBvzz13SMGZBfWl8ZA5Fq8BXd1HONRjikGw6N2qOx0UJLbtsLEkchtM1jXmN85tEu2rAmqBzlnGZwo4onj45bPqjLDweDd7QygK_Yme59xluD9BptFpoCF64aMloH9Aza1Wk1fLtUOOi22EW7dcAt-Uj5SLmD4oxXmGUHd4ml90Qrk5h8OjcCEjWaIrQWe8XafmOJX0gSWmMuYywxxCQalMzZmlEMjua6NkTC5gJEOVAQ3LKOMc-FUiiZkm67bdxraLG0neqHdrB835B9zqQ1g)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: Enter movie ID
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query movie by ID
    Database->>Interface: Return movie
    alt Movie not found
        Interface-->>Interface: Display error message (Movie not found)
    else Interface->>Database: Query user likes for movie
        Database->>Interface: Return user likes
        alt Like not found
            Interface-->>Interface: Display error message (Like not found)
        else Remove like
            Interface->>Database: Remove like
            Database-->>Interface: Return success message
            Interface-->>User: Display success message
        end
    end

```

## request my views

[![](https://mermaid.ink/img/pako:eNp1UT1vwyAQ_SvoZucPMGSyVGXo0EZdKpYLnFskG9zjaGRF-e_FcbBaxWUB3fvgwbuAjY5AQ6KvTMFS6_GDcTBBlTUii7d-xCDqLRE_Tg9BiDu09Ai1KHjCtIEcKSUfgwkLNDvv9vvVSiuewyQp-7enc1poK164dwetnkiUzcxUbPOa8A7_NX0lyRw22L-Na2itXjLxVBOo03QTqEO7aCpv84p_Yhfu_FStWp_GHldzaGAgHtC70sNlVhmQTxrIgC5HRx3mXgyYcC1UzBKPU7CghTM1kEeHUmsD3WGfypScl8jPS7e3ihsof_8eY-VcfwCOja_-?type=png)](https://mermaid.live/edit#pako:eNp1UT1vwyAQ_SvoZucPMGSyVGXo0EZdKpYLnFskG9zjaGRF-e_FcbBaxWUB3fvgwbuAjY5AQ6KvTMFS6_GDcTBBlTUii7d-xCDqLRE_Tg9BiDu09Ai1KHjCtIEcKSUfgwkLNDvv9vvVSiuewyQp-7enc1poK164dwetnkiUzcxUbPOa8A7_NX0lyRw22L-Na2itXjLxVBOo03QTqEO7aCpv84p_Yhfu_FStWp_GHldzaGAgHtC70sNlVhmQTxrIgC5HRx3mXgyYcC1UzBKPU7CghTM1kEeHUmsD3WGfypScl8jPS7e3ihsof_8eY-VcfwCOja_-)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: request reviews
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query reviews by user ID
    Database->>Interface: Return reviews
    Interface-->>User: Display reviews
```

## request my likes

[![](https://mermaid.ink/img/pako:eNptkbFuxCAMhl8FeU5fgOGmSNUNHdpTl4rFB06LmkBqzBCd7t1LjiNqdWFC_j___sEXsNERaEj0kylY6j1-Mk4mqHJmZPHWzxhEvSfix-oxCPGAlh6lHgXPmHaUE6XkYzChSqvz0-GwWWnFa5gkavTflCq0qYW892v1TKJsZqZimrd8d_m_5RtJ5rBD_zVukbV6zcRLna_Oyw1Xx752NGp3wG7kQq6P1Kr3aR5xaRR0MBFP6F3ZwGXtMiBfNJEBXa6OBsyjGDDhWlDMEk9LsKCFM3WQZ4fSFgZ6wDGVKjkvkV_qVm_L7aD8-keMjbn-ArcqrRQ?type=png)](https://mermaid.live/edit#pako:eNptkbFuxCAMhl8FeU5fgOGmSNUNHdpTl4rFB06LmkBqzBCd7t1LjiNqdWFC_j___sEXsNERaEj0kylY6j1-Mk4mqHJmZPHWzxhEvSfix-oxCPGAlh6lHgXPmHaUE6XkYzChSqvz0-GwWWnFa5gkavTflCq0qYW892v1TKJsZqZimrd8d_m_5RtJ5rBD_zVukbV6zcRLna_Oyw1Xx752NGp3wG7kQq6P1Kr3aR5xaRR0MBFP6F3ZwGXtMiBfNJEBXa6OBsyjGDDhWlDMEk9LsKCFM3WQZ4fSFgZ6wDGVKjkvkV_qVm_L7aD8-keMjbn-ArcqrRQ)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database
    participant Session

    User->>Interface: request likes
    Interface->>Session: Get current user
    Session->>Interface: Return current user
    Interface->>Database: Query likes by user ID
    Database->>Interface: Return likes
    Interface-->>User: Display likes

```

## movie genres

[![](https://mermaid.ink/img/pako:eNplUb1uAjEMfhXLM32BDEwwdOjQVl2qLCbng0iX5Oo4VU-Id68PegiKJ8vfj_-OGErH6LDyV-MceBNpL5R8BouRRGOII2WFj8ryWH3OytJT4EdoQ0o7qoZcsNngab2-Khxs5xQ8DrEq7DkLV48QSkqUu4voyjblYujgtbFMQMMAqXxHU525C37f5I21Sb4j3prejvOjQmGZBHop6U8GNg_UImo9xwPt2Ja07tN_O_Obt3SwiXUcaFqWyrjCxJIodnbq4yzzqAdO7NFZ2nFPbVCPPp-MSk3L-5QDOpXGK2xjR7p8Bl1PQ7Uqd1GLvFzed_7iCu3wn6UsnNMvdHml-g?type=png)](https://mermaid.live/edit#pako:eNplUb1uAjEMfhXLM32BDEwwdOjQVl2qLCbng0iX5Oo4VU-Id68PegiKJ8vfj_-OGErH6LDyV-MceBNpL5R8BouRRGOII2WFj8ryWH3OytJT4EdoQ0o7qoZcsNngab2-Khxs5xQ8DrEq7DkLV48QSkqUu4voyjblYujgtbFMQMMAqXxHU525C37f5I21Sb4j3prejvOjQmGZBHop6U8GNg_UImo9xwPt2Ja07tN_O_Obt3SwiXUcaFqWyrjCxJIodnbq4yzzqAdO7NFZ2nFPbVCPPp-MSk3L-5QDOpXGK2xjR7p8Bl1PQ7Uqd1GLvFzed_7iCu3wn6UsnNMvdHml-g)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database

    User->>Interface: Enter "list genres" command
    Interface->>Database: Query all movies
    Database->>Interface: Return movies
    Interface->>Interface: Extract genres from movies and sort alphabetically
    Interface-->>User: Display genres
```

## movie years

[![](https://mermaid.ink/img/pako:eNplkT1uwzAMha9CcE4voCGTO3To0BZdCi-sRCcC9ONSVFEjyN0r13aQNJoEvo_vgeQJbXaMBgt_VU6WO08HodgnaG8kUW_9SEnhvbDcV5-Ssgxk-V7qSOmTSlMWbTZ42O8vHQZkziwKE5OUBbqojdwMDLxUlgkoBIj52_PKbvqt6StrlXQDXptekY8_KmTXeBgkx7ULKDkoWRTsUXLKIR-8benTf7vmN09loPNlDDRtk-AOI0sk79pmT3NXj3rkyD2a9nU8UA3aY5_ODaWq-W1KFo1K5R3W0ZFuh0AzUCitys5rluflWn9H22Hb80fOG3P-BTNSoJg?type=png)](https://mermaid.live/edit#pako:eNplkT1uwzAMha9CcE4voCGTO3To0BZdCi-sRCcC9ONSVFEjyN0r13aQNJoEvo_vgeQJbXaMBgt_VU6WO08HodgnaG8kUW_9SEnhvbDcV5-Ssgxk-V7qSOmTSlMWbTZ42O8vHQZkziwKE5OUBbqojdwMDLxUlgkoBIj52_PKbvqt6StrlXQDXptekY8_KmTXeBgkx7ULKDkoWRTsUXLKIR-8benTf7vmN09loPNlDDRtk-AOI0sk79pmT3NXj3rkyD2a9nU8UA3aY5_ODaWq-W1KFo1K5R3W0ZFuh0AzUCitys5rluflWn9H22Hb80fOG3P-BTNSoJg)

```
sequenceDiagram
    participant User
    participant Interface
    participant Database

    User->>Interface: request years
    Interface->>Database: Query all movies
    Database->>Interface: Return movies
    Interface->>Interface: Extract years from movies and sort chronologically
    Interface-->>User: Display years

```

## CLI Screen - guest home

[![](https://mermaid.ink/img/pako:eNpVj8EKwjAMhl-l5NTB9gIVBN1EBT2pF-mlrNlWWNvRpaCMvbt16sGcwpeP5M8EtdcIAtqgho5dq5V0LNWG833EkdjBW8wyVhRrtuUn37Kjy77KAkteBlSE7DZi-JtUfPcwlBDkYDFYZXS6M70VCdShRQkitRobFXuSIN2cVBXJX56uBkEhYg5x0Gl9ZVRKaEE0qh8TRW3Ih_Mn-_JCDoNyd-9_zvwCdkBDwQ?type=png)](https://mermaid.live/edit#pako:eNpVj8EKwjAMhl-l5NTB9gIVBN1EBT2pF-mlrNlWWNvRpaCMvbt16sGcwpeP5M8EtdcIAtqgho5dq5V0LNWG833EkdjBW8wyVhRrtuUn37Kjy77KAkteBlSE7DZi-JtUfPcwlBDkYDFYZXS6M70VCdShRQkitRobFXuSIN2cVBXJX56uBkEhYg5x0Gl9ZVRKaEE0qh8TRW3Ih_Mn-_JCDoNyd-9_zvwCdkBDwQ)

```
graph TD;
    A((Guest Home)) --> B(Log In)
    A --> C(Create User)
    A --> D(Exit)

```

## CLI Screen - user home

[![](https://mermaid.ink/img/pako:eNpVkN1qwzAMRl9F6MqB9AUyKLTL1l40FLof2PCNVqutWW0Hx-4Ipe8-Ne1g0ZX4zhFIOuM2GMYK95HaA7zWD9qD1Eypt44jLIPjooDJZApz1fSw4ZPln664WwN4vIKV_eZxXKt3UaEJMjEmT2oV9rDO6Z7WQ_qsbip89bBgH3lEF__oB1MUiCU6jo6skf3PV1ljOrBjjZW0hneUj0mj9hdRKafw0vstVilmLjG3hhLXluRyh9WOjp2kbGwKsbn9ZHhNiS35zxD-nMsvqkhclQ?type=png)](https://mermaid.live/edit#pako:eNpVkN1qwzAMRl9F6MqB9AUyKLTL1l40FLof2PCNVqutWW0Hx-4Ipe8-Ne1g0ZX4zhFIOuM2GMYK95HaA7zWD9qD1Eypt44jLIPjooDJZApz1fSw4ZPln664WwN4vIKV_eZxXKt3UaEJMjEmT2oV9rDO6Z7WQ_qsbip89bBgH3lEF__oB1MUiCU6jo6skf3PV1ljOrBjjZW0hneUj0mj9hdRKafw0vstVilmLjG3hhLXluRyh9WOjp2kbGwKsbn9ZHhNiS35zxD-nMsvqkhclQ)

```
graph TD;
    A((User Home)) --> B(My Reviews)
    A --> C(My Likes)
    A --> D(View Movies)
    A --> E(Log Out)
    D --> F(Movies by Genre)
    D --> G(Movies by Year)

```

## CLI Screen - movie details

[![](https://mermaid.ink/img/pako:eNptkU1LxDAQhv9KmHMXUW8VhGq1fnQ9VDxouoehme0Gm6SkaUWW_e9mmwob2JyG93lgyDt7aIwgSKG12O9YWd3UmvmX8bWZJLGcHMpu2LDV6pbdLeEbKtos3gzu-SehjaKcF6RtrD3wXFpqnInVR55NZLElVqGTuo1gwSvyO3-GJS3m9Il_DGTZJQswYs-BXZ1jL4FdxyxseuWZEBcVKTPROV6e8lJ-H_8GCSiyCqXwFe6Pdg1uR4pqSP0oaItj52qo9cGrODrz_qsbSJ0dKYGxF-gol-jLV5BusRt8SkL6htbhLPN1EuhRfxnz7xz-AFrFhV8?type=png)](https://mermaid.live/edit#pako:eNptkU1LxDAQhv9KmHMXUW8VhGq1fnQ9VDxouoehme0Gm6SkaUWW_e9mmwob2JyG93lgyDt7aIwgSKG12O9YWd3UmvmX8bWZJLGcHMpu2LDV6pbdLeEbKtos3gzu-SehjaKcF6RtrD3wXFpqnInVR55NZLElVqGTuo1gwSvyO3-GJS3m9Il_DGTZJQswYs-BXZ1jL4FdxyxseuWZEBcVKTPROV6e8lJ-H_8GCSiyCqXwFe6Pdg1uR4pqSP0oaItj52qo9cGrODrz_qsbSJ0dKYGxF-gol-jLV5BusRt8SkL6htbhLPN1EuhRfxnz7xz-AFrFhV8)

```
graph LR;
    A[Movie Details] --> B[Movie Name]
    A --> C[Year]
    A --> D[Genre]
    A --> E[Director]
    A --> F[Average Rating]
    A --> G[Reviews]
    G --> H[User 1 Review]
    G --> I[User 2 Review]
    G --> J[User 3 Review]
    A --> K[Add/Remove Review]
    A --> L[Add/Remove Like]

```

## use case guest user

```
@startuml
left to right direction

actor Guest as g
g -> (Sign In)
g -> (Create User)

rectangle Guest {
  (Sign In)
  (Create User)
}
@enduml
```

## use case - user

```
@startuml
left to right direction

actor User as u
u -> (View Movies)
u -> (View My Reviews)
u -> (View My Likes)


rectangle {
  (View Movies)
  (View Movies by Genre)
  (View Movies by Year)
}


rectangle "View Movie Details" as details {
  (View Details)
  (Add Review)
  (Remove Review)
  (Add Like)
  (Remove Like)
}

(View Movies by Genre) --> details
(View Movies by Year) --> details
(View Movies) --> (View Movies by Genre)
(View Movies) --> (View Movies by Year)
(View Details) --> (Add Review)
(View Details) --> (Remove Review)
(View Details) --> (Add Like)
(View Details) --> (Remove Like)

@enduml

```