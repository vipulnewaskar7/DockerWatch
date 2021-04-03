export const AppConfig = {
    Address: {
        Login:"https://rahul.jedhe.in/api/dockerwatch/login.php",
        GetAllHost:"https://rahul.jedhe.in/api/dockerwatch/getallhosts.php",
        GetHost:"",
        SaveHost:"https://rahul.jedhe.in/api/dockerwatch/savehost.php",
        DeleteHost:"https://rahul.jedhe.in/api/dockerwatch/deletehost.php",
        GetImages:"http://localhost:8080/api/v1/images",
        GetContainers:"http://localhost:8080/api/v1/containers",
        GetLogs:"http://localhost:8080/ws",
        LogTopic:"/subscribe/topic/logs",
        ContainerListsTopic:"/subscribe/topic/containerlists",
        ImageListsTopic:"/subscribe/topic/imagelists",
        SendLogMessage:"/app/logs",
        SendContainerListsMessage:"/app/containerlists",
        SendImageListsMessage:"/app/imagelists",
    }
}