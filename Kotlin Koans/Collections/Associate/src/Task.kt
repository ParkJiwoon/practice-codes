// Build a map from the customer name to the customer
fun Shop.nameToCustomerMap(): Map<String, Customer> =
        this.customers.associateBy { it.name }

// Build a map from the customer to their city
fun Shop.customerToCityMap(): Map<Customer, City> =
        this.customers.associateWith { it.city }

// Build a map from the customer name to their city
fun Shop.customerNameToCityMap(): Map<String, City> =
        this.customers.associate { it.name to it.city }
