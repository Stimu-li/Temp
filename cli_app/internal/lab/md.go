package lab

import _ "embed"

//go:embed CS_LAB.md
var CSLabContent string

func GetCSLabContent() string {
	return CSLabContent
}
