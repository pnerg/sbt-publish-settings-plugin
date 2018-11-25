/**
  *  Copyright 2018 Peter Nerg
  *
  *  Licensed under the Apache License, Version 2.0 (the "License");
  *  you may not use this file except in compliance with the License.
  *  You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  *  Unless required by applicable law or agreed to in writing, software
  *  distributed under the License is distributed on an "AS IS" BASIS,
  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  *  See the License for the specific language governing permissions and
  *  limitations under the License.
  */
package org.dmonix.sbt

import sbt.Keys.{pomIncludeRepository, publishMavenStyle, _}
import sbt.{AutoPlugin, PluginTrigger, Plugins, URL, plugins, _}

/**
  * Plugin to set the necessary settings to be able to deploy to Maven Central.
  * @author Peter Nerg
  */
object MavenCentralPlugin extends AutoPlugin {
  override def trigger: PluginTrigger = noTrigger
  override def requires: Plugins = plugins.JvmPlugin

  //pre-defined Developers
  def githubDeveloper(id:String, name:String, email:Option[String] = None): Developer = Developer(id, name, email.getOrElse(""), new URL(s"https://github.com/$id"))
  
  //pre-defined SCM's
  def github(project:String):ScmInfo = ScmInfo(new URL(s"https://github.com/$project"), s"git@github.com:$project.git", Some(s"scm:git:git@github.com/$project.git"))
  
  //pre-defined licenses
  def apache:(String, URL) = ("Apache", new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
  def gplv1:(String, URL) = ("GPLv1", new URL("http://www.gnu.org/licenses/gpl-1.0.html"))
  def gplv2:(String, URL) = ("GPLv2", new URL("http://www.gnu.org/licenses/gpl-2.0.html"))
  def gplv3:(String, URL) = ("GPLv3", new URL("http://www.gnu.org/licenses/gpl-3.0.html"))
  def lgplv21:(String, URL) = ("LGPLv2.1", new URL( "http://www.gnu.org/licenses/lgpl-2.1.html"))
  def lgplv3:(String, URL) = ("LGPLv3", new URL( "http://www.gnu.org/licenses/lgpl-3.0.html"))
  def mit:(String, URL) = ("MIT", new URL( "https://opensource.org/licenses/MIT"))
  
  override lazy val projectSettings = Seq(
    credentials in GlobalScope ++= CredentialsSettings.publishCredentials,
    publishTo in GlobalScope := {
      val repoURL = "https://oss.sonatype.org/"
      if (version.value.endsWith("-SNAPSHOT"))
        Some("snapshots" at repoURL+"content/repositories/snapshots")
      else
        Some("releases" at repoURL+"service/local/staging/deploy/maven2")
    },
    publishMavenStyle in GlobalScope := true,
    pomIncludeRepository in GlobalScope := { _ => false }
  )
}
