/*
Copyright © 2025 Abinand P <abinand0911@gmail.com>
*/
package cmd

import (
	"fmt"
	"os"

	"github.com/Stimu-li/Temp/internal/lab"
	"github.com/charmbracelet/glamour"
	"github.com/spf13/cobra"
)

var rootCmd = &cobra.Command{
	Use:   "Temp",
	Short: "A simple application which can help users",
	Long:  ``,
	Run: func(cmd *cobra.Command, args []string) {
		question, _ := cmd.Flags().GetString("question")
		if question == "" {
			fmt.Println("Please provide a question using the -q flag.")
			return
		}
		client := lab.NewClient(lab.GetAPIKey())
		if client == nil {
			fmt.Println("Failed to create client. Check your API key.")
			return
		}
		systemPrompt, _ := cmd.Flags().GetString("prompt")
		response, err := client.Chat(question, systemPrompt)
		if err != nil {
			fmt.Println("Error:", err)
			return
		}
		out, err := glamour.Render(response, "dark")
		if err != nil {
			fmt.Println("Error rendering markdown:", err)
			// Fallback to printing raw response
			fmt.Println("Response:", response)
			return
		}
		fmt.Print(out)
	},
}

func Execute() {
	err := rootCmd.Execute()
	if err != nil {
		os.Exit(1)
	}
}

func init() {
	rootCmd.Flags().StringP("question", "q", "", "Question to ask the AI")
	rootCmd.Flags().StringP("prompt", "p", "", "System prompt to provide context")
}
