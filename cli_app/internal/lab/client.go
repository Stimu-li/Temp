package lab

import (
	"context"

	"google.golang.org/genai"
)

type Client struct {
	apiKey  string
	client  *genai.Client
	context context.Context
}

func NewClient(apiKey string) *Client {
	ctx := context.Background()
	client, err := genai.NewClient(ctx, &genai.ClientConfig{
		APIKey: apiKey,
	})
	if err != nil {
		return nil
	}
	return &Client{
		apiKey:  apiKey,
		client:  client,
		context: ctx,
	}
}

func (c *Client) Chat(prompt string) (string, error) {
	contextValue := GetCSLabContent()
	prompt = "You are a helpful assistant and have a great understanding of the Cloud and Network Security. Here is your context: " + `
	=============================================\n   ` + contextValue + "\n=============================================\n   " + "Please Answer the following question with the help of the question: " + prompt
	result, err := c.client.Models.GenerateContent(c.context, "gemini-2.5-flash", genai.Text(prompt), nil)
	if err != nil {
		return "", err
	}
	return result.Text(), nil
}
