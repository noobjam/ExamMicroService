@Configuration
public class GraphQLConfigurer {

    @Bean
    public GraphQLSchema graphQLSchema(CommandeQuery commandeQuery, CommandeMutation commandeMutation) {
        return SchemaParser.newParser()
                .file("graphql/schema.graphqls")
                .resolvers(commandeQuery, commandeMutation)
                .build()
                .makeExecutableSchema();
    }

    @Bean
    public GraphQL graphQL(GraphQLSchema schema) {
        return GraphQL.newGraphQL(schema).build();
    }
}
