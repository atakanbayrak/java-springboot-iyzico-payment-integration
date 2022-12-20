<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
</head>
<body>
    <style>
        body{text-align: center; padding: 150px;}
        h1{font-size: 50px;}
        body{font: 20px Helvetica,sans-serif; color: #333;}
        article{display: block; text-align: left; width: 650px; margin: 0 auto;}
        a{color: #dc8100; text-decoration: none;}
        a:hover{color: #333; text-decoration: none;}
    </style>
    
        <article>
            <h1>Odeme Sonucu Sayfasi</h1>
            
            <div>
                <h2>Status : ${contentCallBackStatus}</h2>
                <h2>Conversation ID: ${contentCallBackConversationId}</h2>
                <h2>${contentCallBackError}</h2>
                <h2>${contentCallBackErrorCode}</h2>
                <h2>${contentCallBackErrorGroup}</h2>
                
            </div>
            
        </article>
    
</body>
</html>