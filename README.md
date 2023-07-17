# "Get final price feature" | Technical task for K-LAGAN/Inditex.
#### _By Romulo Romero | romuloromero898@gmail.com | +353899568346_

This app provides the final price of a given product on a specific date.

## Technical Info
 - Built using Spring Boot
 - H2 as in-memory database.
 - Swagger for API docs, available at: http://localhost:8080/swagger-ui/index.html
 - Exmaple request URL: http://localhost:8080/get-rate?applicationDate=2020-06-16-10.00.00&productId=35455

## Considerations
- For date formats, I used the one provided in the data example, i.e: yyyy-MM-dd-HH.mm.ss
- In the input parameters, the field "String Identifier", I am using it as an unique transaction identifier.
- In the output parameters, the field "chain identifier", I couldn't figure out what it means, so I'm just returning the same identifier provided in the request.
- In the output parameters, the field "rate to apply", returns the record found in the Prices table.
- In case more than one record is found for a product in the table Prices, I select the one with the highest priority.
- New tables created: Product and Brand, populated with basic info.
- In case the product isn't found in the table prices, I return the list price of the product (Field in the table Product) and Rate-To-Apply is set to "LIST PRICE".
- In case the product isn't found in the table prices nor in the table product, a product with PRODUCT_ID=0 is returned, I chose this approach instead of throwing an exception.
- In case an unexpected exception is thrown, there is a list of errors messages in the response. 

**Thank you!**
