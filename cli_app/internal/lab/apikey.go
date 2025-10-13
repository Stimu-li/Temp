package lab

var APIKey string

func GetAPIKey() string {
	if APIKey == "" {
		return ""
	}
	return APIKey
}
