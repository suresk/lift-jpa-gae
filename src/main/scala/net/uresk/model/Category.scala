package net.uresk.model

import javax.persistence._
import com.google.appengine.api.datastore.Key

@Entity
class Category {

  @Id
  @GeneratedValue(){val strategy = GenerationType.IDENTITY}
  var id : Key = _
  
  @Column(){val nullable = false}
  var name : String = ""
  
  @Column(){val nullable = true}
  var more : String = ""
  
  override def toString(): String = {
    new String("ID: " + id + ", NAME: " + name + ", MORE: "  + more);
  }
  
}