@echo off
Set privateKeyPath=私钥证书路径
Set mappingFilePath=配置连接的文件
Set jumpHost=远程服务器IP
Set dbuser=数据库用户名
java -jar ./SSHPFL.jar  %privateKeyPath% %jumpHost% %dbuser% %mappingFilePath%