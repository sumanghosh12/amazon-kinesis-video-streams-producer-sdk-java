## Amazon Kinesis Video Streams Producer SDK Java

## License

This library is licensed under the Amazon Software License.

## Introduction

Amazon Kinesis Video Streams makes it easy to securely stream video from connected devices to AWS for analytics, machine learning (ML), and other processing.

The Amazon Kinesis Video Streams Producer SDK Java makes it easy to build an on-device application that securely connects to a video stream, and reliably publishes video and other media data to Kinesis Video Streams. It takes care of all the underlying tasks required to package the frames and fragments generated by the device's media pipeline. The SDK also handles stream creation, token rotation for secure and uninterrupted streaming, processing acknowledgements returned by Kinesis Video Streams, and other tasks.

## Resources

* [Developer Guide](https://docs.aws.amazon.com/kinesisvideostreams/latest/dg/producer-sdk-javaapi.html) - For in-depth getting started and usage information.
* [Release Notes](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-java/releases) - To see the latest features, bug fixes, and changes in the SDK
* [Issues](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-java/issues) - Report issues and submit pull requests (see [Amazon Software License 1.0](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp/blob/master/LICENSE))


### Prerequisites

* You can find available pre-built KinesisVideoProducerJNI library in [src/main/resources/lib/](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-java/tree/master/src/main/resources/lib) for Mac (x64), Ubuntu (x64) and Raspian (x86) and Windows 10. If pre-built libraries did not work for you, ["KinesisVideoProducerJNI"](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp) native library needs to be built first before running the Java demo application. Please follow the steps  in the section **Build the native library (KinesisVideoProducerJNI) to run Java Demo App** in Producer SDK CPP [readme](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp).

### Building from Source

Import the Maven project to your IDE, it will find dependency packages from Maven and build.

### Examples

#### Launching Demoapp sample application

Run `DemoAppMain.java` in `./src/main/demo` with JVM arguments set to
```
-Daws.accessKeyId=<YourAwsAccessKey> -Daws.secretKey=<YourAwsSecretKey> -Djava.library.path=<NativeLibraryPath>
```
for **non-temporary** AWS credential.

```
-Daws.accessKeyId=<YourAwsAccessKey> -Daws.secretKey=<YourAwsSecretKey> -Daws.sessionToken=<YourAwsSessionToken> -Djava.library.path=<NativeLibraryPath>
```
for *temporary* AWS credential.

**Note**: NativeLibraryPath must contain your ["KinesisVideoProducerJNI"](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp#build-the-native-library-kinesisvideoproducerjni-to-run-java-demo-app) library. File name depends on your Operating System:
* `libKinesisVideoProducerJNI.so` for Linux
* `libKinesisVideoProducerJNI.dylib` for Mac OS
* `KinesisVideoProducerJNI.dll` for Windows

If you are using pre-built libraries, please specify the path of library. Take pre-build library for Mac as example, you can specify `src/resources/lib/mac` as <NativeLibraryPath>.

Demo app will start running and putting sample video frames in a loop into Kinesis Video Streams. You can change your stream settings in `DemoAppMain.java` before you run the app.

##### Run the demo application from command line

If you want to run the `DemoAppMain`, follow the [steps](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-java/issues/14) below. See [Prerequisites](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-java#prerequisites) to find available native library needed to run `DemoAppMain`.

Change the current working directory to

```
$ cd /<YOUR_FOLDER_PATH_WHERE_SDK_IS_DOWNLOADED>/amazon-kinesis-video-streams-producer-sdk-java/
```

Compile and assemble Java SDK, Java Demoapp and the Maven dependencies
```
$ mvn clean compile assembly:single
```

Start the demo app
```
$ java -classpath target/kinesisvideo-java-demo-1.0-SNAPSHOT-jar-with-dependencies.jar -Daws.accessKeyId=<ACCESS_KEY> -Daws.secretKey=<SECRET_KEY> -Djava.library.path=<NativeLibraryPath> com.amazonaws.kinesisvideo.demoapp.DemoAppMain

```

##### Run the demo application from Docker

Refer the **README.md** file in the  *dockerscripts* folder for running the build and demo app within Docker container.

#### Launching PutMediaDemo sample application

 Run `PutMediaDemo.java` to send sample mkv stream to Kinesis Video Streams. **Note:** ACCESS_KEY and SECRET_KEY are required for running this sample application as well. However, this demo application does not require JNI.

```
-Daws.accessKeyId=<YourAwsAccessKey> -Daws.secretKey=<YourAwsSecretKey>
```
for **non-temporary** AWS credential.

```
-Daws.accessKeyId=<YourAwsAccessKey> -Daws.secretKey=<YourAwsSecretKey> -Daws.sessionToken=<YourAwsSessionToken>
```
#### Pre-built KinesisVideoProducerJNI library supported platforms
* Mac OS X (El capitan 10.11 or above)
* Ubuntu (14.04 or above)
* Raspian (9 stretch or above)

#### Additional Examples

For additional examples on using Kinesis Video Streams Java SDK and  Kinesis Video Streams Parsing Library refer:

##### [Kinesis Video Streams Producer SDK CPP](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp/blob/master/README.md)
##### [Kinesis Video Streams Parser Library](https://github.com/aws/amazon-kinesis-video-streams-parser-library/blob/master/README.md)

##### [Kinesis Video Streams Android](https://github.com/awslabs/aws-sdk-android-samples/tree/master/AmazonKinesisVideoDemoApp)

#### Troubleshooting

If you notice error in loading the native library (JNI), then check the output of `ldd` or `otool`

```
$ ldd libKinesisVideoProducerJNI.so
```
or in MacOS
```
$ otool -L libKinesisVideoProducerJNI.dylib
```

This will provide details on missing libraries during linking; If the output shows missing shared libraries, then run the following commands to clean the `CMakeCache` and link again.

```
rm -rf ./kinesis-video-native-build/CMakeCache.txt ./kinesis-video-native-build/CMakeFiles

```
and run `./install-script` again.

```
./install-script

```
Also, set the `LD_LIBRARY_PATH` as below
```
export LD_LIBRARY_PATH=/<YOUR_PRODUCER_SDK_CPP_DOWNLOAD>/amazon-kinesis-video-streams-producer-sdk-cpp/kinesis-video-native-build/downloads/local/lib:$LD_LIBRARY_PATH
```

This should resolve native library loading issues.

## Release Notes
### Release 1.9.3 (12 March 2019)
* Bug fix to avoid crash due to access to freed native stream object.

### Release 1.9.2 (21 Feburary 2019)
* Bug fix for broken MKV generated due to difference between trackInfoType in Java and C layer.

### Release 1.9.1 (19 Feburary 2019)
* Bug fix for credentials not rotating issue when given credentials expire in less than 40 minutes.
* Add audio video sample to support injesting multiple track data into Kinesis Video.

### Release 1.9.0 (8 Feburary 2019)
* Bug fix for KinesisVideoClient.unregisterMediaSource() accessing to freed native object issue.
* Add KinesisVideoClient.freeMediaSource() clean-up function to handle async behavior.

### Release 1.8.0 (25 January 2019)
* Fix duplicate stream error after unregistering media source when service call failed
* Fix inputstream not closing after stopSync issue
* Updating the name and description of Java SDK to publish in maven

### Release 1.7.0 (3 December 2018)
* Added support for uploading files(offline mode) to Kinesis Video Stream
* Additional fixes

### Release 1.6.0 (3 December 2018)
* Remove streamName parameter from KinesisVideoClient.registerMediaSource() as MediaSource already has the stream name in StreamInfo.
* Add KinesisVideoClient.unregisterMediaSource() to remove MediaSource to KinesisVideoProducerStream binding from KinesisVideoClient. Customers can use unregisterMediaSource() after they stop streaming, so MediaSource data will not to be sent to Kinesis Video Streams.
* Add getStreamInfo() to MediaSource instead of MediaSourceConfiguration. If customers have implemented their own MediaSource and MediaSourceConfiguration, they would need to provide stream information via MediaSource.getStreamInfo(). The MediaSourceConfiguration.getStreamInfo() will not work.
* The following classes are no longer publicly available.
 * MediaSource
 * MediaSourceConfiguration
 * MediaSourceSink
 * AbstractKinesisVideoClient
 * NativeKinesisVideoClient
 * BytesGenerator
 * BytesMediaSource
 * BytesMediaSourceConfiguration
 * ProducerStreamSink
 * KinesisVideoServiceClient
 * NativeKinesisVideoProducerJni
 * NativeKinesisVideoProducerStream
 * NativeLibraryLoader
 * KinesisVideoMetrics
 * KinesisVideoProducer
 * KinesisVideoProducerStream
 * KinesisVideoStreamMetrics
 * ReadResult
 * ServiceCallbacks
 * com.amazonaws.kinesisvideo.service.exception.AccessDeniedException
 * com.amazonaws.kinesisvideo.service.exception.AmazonServiceException
 * com.amazonaws.kinesisvideo.service.exception.ResourceInUseException
 * com.amazonaws.kinesisvideo.service.exception.ResourceNotFoundException
 * AckConsumer
 * BlockingAckConsumer
 * DefaultServiceCallbacksImpl

### Release 1.5.0 (24 August 2018)
* Windows native library available for Producer SDK
* Intermittent producer support
* Per-stream customized callback support

### Release 1.3.1 (23 July 2018)

* Add reset connection function.
* Fix key frame data-flag matching issue which could cause parsing issue in decoding process.

### Release 1.3.0 (15 March 2018)

* Provide pre-built KinesisVideoProducerJNI library for Mac (x64), Ubuntu (x64) and Raspian (x86).
* Remove Lombok dependency on Java Producer SDK.
* Update instruction in README about KinsisVideoProducerJNI build.
* Compatible changes in Java Adapter to work with latest changes in [Kinesis Video Streams Producer SDK CPP](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp/blob/master/README.md).

### Release 1.2.1 (February 2018)

* Remove some unit tests relying on native library to avoid mvn package build (without -skipTests=true) failure.

### Release 1.2.0 (February 2018)

* Bug fixes and performance enhancement.
* There are some interface changes to be compatible with native library changes.

### Release 1.1.0 (December 2017)

* Updated JNI code to expose ACKs as callbacks so developer can get more information about how the streaming is going.
* The version of JNI is bumped to 1.2, this will require corresponding ["KinesisVideoProducerJNI"](https://github.com/awslabs/amazon-kinesis-video-streams-producer-sdk-cpp) with same version.

### Release 1.0.0 (November 2017)

* First release of the Amazon Kinesis Video Producer SDK Java.
