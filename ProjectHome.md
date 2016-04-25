This project contains very thin yet complete xmlrpc client side library for Android platform.

It's usage is as simple as:

```
XMLRPCClient client = new XMLRPCClient("http://foo.bar.com");
// add 2 to 4
int sum = (Integer) client.call("add", 2, 4);
// check whether x is inside range 4..10
boolean isInside = (Boolean) client.call("isInside", x, 4, 10);
// capitalize string
String capitalized = (String) client.call("capitalize", "to be or not to be");
```