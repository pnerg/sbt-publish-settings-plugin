/**
  *  Copyright 2017 Peter Nerg
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

import sbt._

/**
  * Settings to use when configuring the SBT build file for deploying to [[https://oss.sonatype.org/ Maven Central]]
  * @author Peter Nerg.
  */
object MavenCentralSettings {

  /**
    * Figures out the path/URL to where to deploy the artifact.
    *
    * The path depends on if it's a sharp or snapshot release.
    *
    * Snapshot -> `https://oss.sonatype.org/content/repositories/snapshots`
    *
    * Non-snapshot -> `https://oss.sonatype.org/service/local/staging/deploy/maven2`
    *
    * Example on usage in build.sbt:
    * {{{
    * import org.dmonix.sbt.MavenCentralSettings._
    * publishTo <<= version {deployURL(_)}
    * }}}
    *
    * @param artifactVersion The version of the artifact as specified in build.sbt
    * @return The resolver to use
    * @since 0.5
    */
  def publishURL(artifactVersion:String):Option[Resolver] = {
    val repoURL = "https://oss.sonatype.org/"
    if (artifactVersion.endsWith("-SNAPSHOT"))
      Some("snapshots" at repoURL+"content/repositories/snapshots")
    else
      Some("releases" at repoURL+"service/local/staging/deploy/maven2")
  }

}
