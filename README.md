

## 用法
### 创建租户
./pulsar-admin tenants create my-tenant

### 创建命名空间
./pulsar-admin namespaces create my-tenant/test-namespace

### 创建 function
./pulsar-admin functions create \
    --tenant my-tenant \
    --namespace test-namespace \
    --name myfunc \
    --jar /home/pulsar/pulsar/apache-pulsar-2.7.0/lib/pulsar-functions-demo-uppercasefunctions.jar \
    --classname com.hw.pulsar.function.demo.WordCountFunction \
    --inputs persistent://my-tenant/test-namespace/tp1 \
    --output persistent://my-tenant/test-namespace/tp2
    
### 查询function 
./pulsar-admin  functions list  --tenant my-tenant  --namespace test-namespace

### 更新function 
./pulsar-admin functions update
 ..... <其他内容不变>
 
