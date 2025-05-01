package org.example;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpClientTransport;
import io.modelcontextprotocol.spec.McpSchema;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        McpClientTransport transport = HttpClientSseClientTransport.builder("http://localhost:9200/_plugins/_ml/mcp")
                .customizeClient(clientBuilder -> {
            clientBuilder.connectTimeout(Duration.ofSeconds(1000));
        }).build();
        McpSyncClient client = McpClient.sync(transport)
                .requestTimeout(Duration.ofSeconds(10000))
                .capabilities(McpSchema.ClientCapabilities.builder()
                        .roots(true)
                        .sampling()
                        .build())
                .build();

        McpSchema.InitializeResult initResult = client.initialize();
        System.out.println( "Initialized: " + initResult);

        McpSchema.ListToolsResult tools = client.listTools();
        System.out.println("Tools are: " + tools);

        McpSchema.CallToolResult result = client.callTool(
                new McpSchema.CallToolRequest("ListIndexTool",
                        Map.of("input", Map.of("index", List.of("test"))))
        );
        System.out.println("Result is: " + result);

        client.closeGracefully();
    }
}
