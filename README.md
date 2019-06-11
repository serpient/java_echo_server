![gif](https://media.giphy.com/media/MAzusM3FqQzcl4crXq/giphy.gif)

# Echo Server Requirements
- Echo Server connects to a client
- Once running, anything typed in the client will be sent to Server
- Server will echo back the message to the client

# Local Development Setup
### 1. Install Java
Check if Java is present: `java -version`

If not present, follow these [instructions](https://www.notion.so/Setting-Up-Java-Environment-1a48792fb5c6403bbb430c882e411226#3b7fec7b6e6d4f06a82dca4afcf31081).

### 2. Install Gradle
`brew install gradle`
Verify you have it installed with `gradle -v`

### 3. Clone repo
`git clone https://github.com/serpient/java_echo_server.git`

### 4. Run Build to see reports
Within project folder, run `./gradlew build` to see the status

### 5. Run the server
#### Option 1: Default port 1234
Run `gradle run` to start the server

#### Option 2: Custom post
Run `gradle run --args='1111'`. Replace '1111' with your own custom port

### 6. Use netcat to interact with server
Open another terminal and run `nc localhost the_matching_port_number`

# Testing Multiple Requests
- To test the simultaneous client sessions with a benchmark, start App.main() and then run `ab -r -n 5000 -c 1000
http://localhost:1234/` in another terminal.

- To test the simultaneous client sessions in a real life session, start App.main() and then open at least 2 terminals
and run `nc localhost 1234`. From here, you can type an input, and the session should echo back your input and then close itself.

### Benchmark Result of 1500 Simultaneous Requests with Apache AB
![benchmark](https://user-images.githubusercontent.com/29721784/59047037-f5354a80-8837-11e9-94d1-ca5ef7e59160.png)