# E-Commerce
This repo is a microservices for an e-commerce app which was a final project in Fawry internship. I shared my teammate to build it.

## bank-api
- add account (card number, name, password)
- create transaction (deposit/withdraw amount with notes) and log transaction
- account login
- view account balance, transaction details

## coupon-api
- Create coupon : each coupon has a code, number of usages, expiry date, coupon may apply with fixed or percentage value
- Consume coupon for a customer and order
       - reduce number of coupon cnsumptions
       - add record to consumption history
- View coupons and consumption history


## order-api
- Create order
        - if order has coupon, validate and consume coupon from coupon-api
        - consume stock from store-api
        - withdraw amount from customer via bank-api and save transaction ID
        - deposity amount to merchant via bank-api
        - send to orders notifications

- Search orders by customer, date range


## product-api
- Create a product and each product has category
- Search for a product 


## store-api
- create store/warehouse
- search products
- add stock for product with history
- consume products with history


## notification-api
- listen for orders notifications
- send notification to merchant
- send notification to customer

## user-api
- add/activate/deactivate admin users
- view list of users
