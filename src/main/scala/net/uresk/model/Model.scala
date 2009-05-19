package net.uresk.model;
 
import javax.persistence.Persistence
 
import org.scala_libs.jpa._
import net.liftweb.jpa._
 
object Model extends LocalEMF("transactions-optional") with RequestVarEM