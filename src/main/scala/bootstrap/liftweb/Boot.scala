package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._

/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("net.uresk")

    // Build SiteMap
    val entries = Menu(Loc("Home", List("index"), "Home")) :: 
      Menu(Loc("Categories", List("categories", "list"), "View Categories")) :: 
      Menu(Loc("Add Category", List("categories", "add"), "Add Category")) :: Nil
    LiftRules.setSiteMap(SiteMap(entries:_*))
  }
}

