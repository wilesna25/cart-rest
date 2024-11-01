# cart-rest  Done by Wilmer Andres
Springboot

Database model diagram




When the app starts the database is populated with 1 cart with cartId=1 and 3 products

Cart cart = new Cart(1, "cartW");

Product product1 = new Product("product1", 10);
Product product2 = new Product( "product2", 20);
Product product3 = new Product("product3", 30);




Endpoints:


Add product to cart:

http://localhost:8080/v1/swagger-ui/index.html#/cart-controller/addProductToCart


Update product quantity

http://localhost:8080/v1/swagger-ui/index.html#/cart-controller/updateProductQuantity


Deleted product from cart:

http://localhost:8080/v1/swagger-ui/index.html#/cart-controller/deleteProductFromCart



View Cart Endpoint

http://localhost:8080/v1/swagger-ui/index.html#/cart-controller/viewCart




TO RUN IT…

 Create a Postgresql database with the name “cart”
Update user/password credentials for Postgres in src/main/resources/application.properties 
Open the project in IntelliJD
Run maven install to install dependencies from pom.xml
Run maven compile
Run the application
Check in the browser the url http://localhost:8080/v1/swagger-ui/index.html
Test the API :) 


