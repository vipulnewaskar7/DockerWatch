export const AppConfig = {
    Address: {
        Login:"http://localhost:8080/api/v1/login",
        Logout:"http://localhost:8080/api/v1/logout",
        
        GetAllHost:"http://localhost:8080/api/v1/hosts",
        SaveHost:"http://localhost:8080/api/v1/hosts",
        DeleteHost:"http://localhost:8080/api/v1/hosts",

        SocketURL:"http://localhost:8080/ws",
        Subscribe:"/user/.user./topic/dockerwatch",
        
        Images:"/app/images",
        Containers:"/app/containers",
        Logs:"/app/logs"
    }
}
