// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> =
    this.customers.filter {
        val (delivered, undelivered) = it.orders.partition { it.isDelivered }
        delivered.size < undelivered.size
    }.toSet()
