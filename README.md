# Bookshop

# Curl Command to Create Category 
curl --location --request POST 'localhost:8000/bookShop-api/saveCategory' --header 'Content-Type: application/json' --data-raw '{ "title":"WildLife"}'

# Curl Command to Create Book
curl --location --request POST 'localhost:8000/bookShop-api/saveBook' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title":"Book Of Afica",
    "description":"Africa",
    "category":"WildLife",
    "price":12.30
}'

# Curl Command to View All Books
curl --location --request GET 'localhost:8000/bookShop-api/getAllBooks'

# Curl Command to View All Books By Category
curl --location --request GET 'localhost:8000/bookShop-api/getBooksByCategory/WildLife'

# Curl Command to Purchase a book

curl --location --request POST 'localhost:8000/bookShop-api/purchaseBook' \
--header 'Content-Type: application/json' \
--data-raw '{

"bookId":2,
"quantity":2,

"card":{
    "id" : "1234560000000001",
    "expiry" : "2020-01-01"
}

}'
