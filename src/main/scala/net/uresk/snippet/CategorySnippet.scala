package net.uresk.snippet

import scala.xml.{NodeSeq,Text}
 
import net.liftweb.http.{RequestVar,S,SHtml}
import net.liftweb.util.{Helpers,Log}
import S._
import Helpers._

import net.uresk.model._
import net.uresk.model.Model._

import javax.persistence.{EntityExistsException,PersistenceException}

class CategorySnippet {

  def list(xhtml : NodeSeq) : NodeSeq = {
    val categories = Model.createNamedQuery[Category]("findAllCategories").getResultList()
    
    categories.flatMap(category =>
    	bind("category", xhtml, 
    		"name" -> Text(category.name),
    		"more" -> Text(category.more),
    		"edit" -> SHtml.link("add.html", () => categoryVar(category), Text(?("edit")))
    	)
    )
  }
  
  object categoryVar extends RequestVar(new Category())
  def category = categoryVar.is
  
  def add(xhtml : NodeSeq) : NodeSeq = {
    val currentId = category.id
    
    def doAdd() = {
      if(category.name.length == 0){
        error("emptyCategory", "The category name cannot be blank")
      } else {
        try {
          Model.mergeAndFlush(category)
          redirectTo("list.html")
        } catch {
          case ee : EntityExistsException =>
            error("That category already exists")
          case pe : PersistenceException =>
            error("Error adding category")
        }
      }
    }
    
    val current = category
    
    bind("category", xhtml, 
    	"id" -> SHtml.hidden(() => categoryVar(current)),
    	"name" -> SHtml.text(category.name, category.name=_),
    	"more" -> SHtml.text(category.more, category.more=_),
    	"submit" -> SHtml.submit(?("save"), doAdd)
    )
  }
  
}