package lab

import _ "embed"

//go:embed DSUP_LAB.md
var CSLabContent string

func GetCSLabContent() string {
	return CSLabContent
}
