# SBT Publish Settings Plugin
Plugin for managing settings needed for SBT publish.

It can be very cumbersome to configure a _build.sbt_ file properly if one wishes to publish the artifact to some repository (e.g. Nexus, Artifactory).  
This plugin provides help for that configuration.

###Credentials
In order to be able to publish to any repository it generally requires you to provide credentials to that repository.  
Traditionally sbt uses the _~/.ivy2/.credentials_ file for that.  
It however requires some manual setup in the build.sbt file.  
Also the utilities provided out-of-the-box only support one single domain in the _.credentials_ file.  

The example below is a fully working _.credentials_ file with multiple domains.   
```script
#Local Nexus installation
realm=Sonatype Nexus Repository Manager
host=somehost.your.domain
user=some-user
password=oh-so-secret

#Local Artifactory installation
realm=Artifactory Realm
host=somehost.your.domain
user=yet-another-user
password=yet-another-psw

#Maven Central
realm=Sonatype Nexus Repository Manager
host=oss.sonatype.org
user=secret-agent
password=wont-tell-you
```

Add these lines to your _build.sbt_ file to automatically read _~/.ivy2/.credentials_ file and add it to your build.
```scala
import org.dmonix.sbt.CredentialsSettings._
credentials ++= publishCredentials
```

###Publish to Maven central
If you're like me and publish artifacts to [Maven Central](https://oss.sonatype.org) then you might want to use the _MavenCentralSettings_ utility to automatically configure the URLs where to publish your binaries.  
Adding this to your _build.sbt_ file will automatically configure the publish path.  
In fact it takes into account if it's a _-snapshot_ or fixed version release. you want to publish.  
Snapshot -> `https://oss.sonatype.org/content/repositories/snapshots`  
Non-snapshot -> `https://oss.sonatype.org/service/local/staging/deploy/maven2`  

```scala
import org.dmonix.sbt.MavenCentralSettings._
publishTo <<= version {publishURL(_)}
```
Refer to the above _.credentials_ file for an example on how the credentials for Maven Central realm are configured.

###Live Example
Check out the [build.sbt](../master/build.sbt) for this project to see the usage of the plugin.

###Installing the plugin
Simply add this to the _plugins.sbt_ file (of course replacing the version):
```scala
addSbtPlugin("org.dmonix.sbt" % "sbt-publish-settings-plugin" % "use-the-proper-version")
```