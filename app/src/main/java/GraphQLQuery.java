public interface GraphQLQuery<T> {

  String getQuery();

  T parseResponse(String responseBody);
}
