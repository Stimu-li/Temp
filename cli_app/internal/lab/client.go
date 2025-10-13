package lab

import (
	"context"
	"fmt"
	"time"

	"github.com/briandowns/spinner"
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

func (c *Client) Chat(prompt string, systemPrompt string) (string, error) {
	contextValue := GetCSLabContent()
	var fullPrompt string
	if systemPrompt == "" {
		fullPrompt = "You are a Cloud Security Expert. You have to use the provided context to answer the question with relevant programs with proper indent, no comments and finally add methodology- if required(like setup steps). Here is your context: " + contextValue + "\n Answer the following question: " + prompt
	} else {
		fullPrompt = systemPrompt + "\n Answer the following question: " + prompt
	}
	s := spinner.New(spinner.CharSets[14], 100*time.Millisecond)
	s.Prefix = "Processing... "
	s.Start()

	result, err := c.client.Models.GenerateContent(c.context, "gemini-2.5-flash", genai.Text(fullPrompt), nil)

	s.Stop()

	if err != nil {

		fmt.Println("Error during content generation.")
		return "", err
	}

	return result.Text(), nil
}
