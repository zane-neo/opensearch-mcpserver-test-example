# opensearch-mcpserver-test-example
This is an example project that can communicate to OpenSearch MCP APIs. Please refer to this PR: opensearch-project/ml-commons#3781 to learning more about MCP in OpenSearch. The steps to test OpenSearch MCP server are:

1. Start your OpenSearch cluster with security plugin disabled.
2. Register MCP tools with the register mcp tools API:
```json
POST /_plugins/_ml/mcp/tools/_register
{
    "tools": [
        {
            "name": "ListIndexTool",
            "description": "This is my first list index tool",
            "params": {},
            "schema": {
                "type": "object",
                "properties": {
                    "indices": {
                        "type": "array",
                        "items": {
                            "type": "string"
                        },
                        "description": "OpenSearch index name list, separated by comma. for example: [\"index1\", \"index2\"], use empty array [] to list all indices in the cluster"
                    }
                },
                "additionalProperties": false
            }
        }
    ]
}
```
3. Run the Main.main method to see the printed results.
