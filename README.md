# Assignment 13 for Spring Boot lesson

## POST 

Create one cart.

`POST localhost:8080/cart/save`

```
# Request body
{
  "cartModelName": "wonderful",
  "cartModelPrice": 5.0,
  "cartModelShortDesc": "blue sky",
  "cartModelQuantity": 3000  
}

# Response
{
  "status": "Successful",
  "message": "A new cart is created successfully."
}
```

## GET one cart

Get one cart.

`GET localhost:8080/cart/3`

```
{
  "status": "Successful",
  "data": {
    "cartModelName": "Shape-changing container set",
    "cartModelPrice": 85.0,
    "cartModelShortDesc": "Mix and match your containers to fit your food content.",
    "cartModelQuantity": 150
  }
}
```

## GET all

Get all carts.

`GET localhost:8080/cart/all`

```
{
  "status": "Successful",
  "data": [
    {
      "cartModelName": "Super useful cutting set",
      "cartModelPrice": 45.0,
      "cartModelShortDesc": "A collection of useful vegetables cutting tools.",
      "cartModelQuantity": 100
    },
    {
      "cartModelName": "Multi-purpose cooking oven",
      "cartModelPrice": 140.0,
      "cartModelShortDesc": "Oven for baking, steaming, microwaving and grilling.",
      "cartModelQuantity": 170
    },
    {
      "cartModelName": "Shape-changing container set",
      "cartModelPrice": 85.0,
      "cartModelShortDesc": "Mix and match your containers to fit your food content.",
      "cartModelQuantity": 150
    },
    {
      "cartModelName": "wonderful",
      "cartModelPrice": 5.0,
      "cartModelShortDesc": "blue sky",
      "cartModelQuantity": 3000
    }
  ]
}
```