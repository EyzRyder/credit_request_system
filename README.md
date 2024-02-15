# Credit App

## Path

```bash
- api
 L customer
    L[Post] /
    L[Get] /{id}
    L[Patch] ?customerId=
    L[Delete] /{id}
 L credit
  L[Post] /
  L[Get] /?customerId=
  L[Get] /{creditCode}?customerId=
```

### POSTs

- http://localhost:3030/api/customer
  - Body
    ```
    {    
    "firstName": "String",
    "lastName": "String",
    "cpf": "String",
    "income": 1000,
    "email": "String",
    "password": "String",
    "zipCode": "String",
    "street": "String",
    }
    ```
- http://localhost:3030/api/credit
    - Body
      ```
      {
        "creditValue": 1000.00,
        "dayFirstOfInstallment": 2004-01-01,
        "numberOfInsdtallments": 8,
        "customerId": 1
       }
       ```
