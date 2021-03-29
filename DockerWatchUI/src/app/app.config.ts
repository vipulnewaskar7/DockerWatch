export const AppConfig = {
    Address: {
        Login:"http://localhost:8080/api/v1/login",
        Logout:"http://localhost:8080/api/v1/logout",
        
        GetAllHost:"http://localhost:8080/api/v1/hosts",
        SaveHost:"http://localhost:8080/api/v1/hosts",
        DeleteHost:"http://localhost:8080/api/v1/hosts",

        GetImages:"/images",
        GetContainers:"/containers",
        GetLogs:"/logs",
        LogTopic:"/subscribe/logs",
        SendLogMessage:"/containerslogs"
    }
}