### jwt的三段构成

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ

```
* 其中两个"."将token串分成了三串每一串都有特定得功能
###### 1. 第一串：header

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
```
* 主要信息

```
{
  'typ': 'JWT', //声明类型
  'alg': 'HS256' //声明加密算法
}

```
* 然后将头部进行base64加密（该加密是可以对称解密的),构成了第一部分.
###### 2.第二串 ：playload

```
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9
```
 * 主要信息
 1. 标准中注册的声明
 2. 公共的声明(公共的声明可以添加任何的信息，一般添加用户的相关信息或其他业务需要的必要信息.但不建议添加敏感信息，因为该部分在客户端可解密.)
 3. 私有的声明(私有声明是提供者和消费者所共同定义的声明，一般不建议存放敏感信息，因为base64是对称解密的，意味着该部分信息可以归类为明文信息。)
 * 然后将其进行base64加密，得到Jwt的第二部分。
######  3.第三串：signature

```
TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
```
* 主要信息
1. header (base64后的)
2. payload (base64后的)
3. secret
* signature=header+payload 在拿secret结合前面(header)得加密算法加密。
* signature得作用：防止jwt被篡改

### 优点
* 因为json的通用性，所以JWT是可以进行跨语言支持的，像JAVA,JavaScript,NodeJS,PHP等很多语言都可以使用。
* 因为有了payload部分，所以JWT可以在自身存储一些其他业务逻辑所必要的非敏感信息。
* 便于传输，jwt的构成非常简单，字节占用很小，所以它是非常便于传输的。
* 它不需要在服务端保存会话信息, 所以它易于应用的扩展

### 使用注意
* 不应该在jwt的payload部分存放敏感信息，因为该部分是客户端可解密的部分。
* 保护好secret私钥，该私钥非常重要。
* 如果可以，请使用https协议



