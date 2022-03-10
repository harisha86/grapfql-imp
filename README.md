# grapfql-imp
 GraphQL Implementation


# Method : Post
# URL : http://localhost:8091/rest/books

# All Bookshelf
{
   allBooks {
       isn,
       authors
   }
}

# Specific
 {
    book(idfromgraphql:"124"){
        isn,
        authors
    }
}
