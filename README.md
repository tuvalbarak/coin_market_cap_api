# CoinMarketCap API Application

I used "https://pro-api.coinmarketcap.com" to fetch data.

Overview -
The app is fully working while all the required functionalities are implemented.
The app displays the top 100 coins, sorted by their market cap value.
The list shows the following parameters of every coin:
- Symbol
- Last Updated Date
- Number of Market Pairs
- Max Supply

Whenever a coin is clicked, the user will see an extended view which will contain in addition to the previous parameter:
- Name
- Market Cap Dominance
- Price
- Volume Change in the past 24 hours

Architecture - 
- I wrote the app using the MVVM architecture, thus achieving a total separation of layers, which makes the app scalable and gives me the ability to easily change and test each module. 
- I tried to use as much as I can in the Observer design pattern, to achieve a reusable and readable code.
- The app is written based on the Single-Activity architecture. This helps me to take advantage of the use of fragments as much as possible.

Technologies - 
- The app is written in Kotlin, and it uses LiveData to enable stateless app, Repository to create an abstract layer between the Viewmodel and the API, Viewmodel to perform the entire app logic, Coroutines to achieve better performance with asynchronous functions, and Retrofit to fetch data using API calls.

How to Make It Better -
- In a production app, I would add Pagination and some cache mechanism to enable a faster loading and an offline experience.
- I would let ther user create a portfolio which will be saved on his device.
- If a user clicks on a coin, he will be moved to another screen so he will be able to get the entire data about the coin.
- I would create Unit testing.


Below you will find a link to a short YouTube video I made to demonstrate the app.

https://youtu.be/8pKVWtD-hUQ
