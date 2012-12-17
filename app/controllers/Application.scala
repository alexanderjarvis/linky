package controllers

import scala.concurrent.Future
import play.api._
import play.api.mvc._
import play.api.libs.json._
import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.handlers.DefaultBSONHandlers._
import play.modules.reactivemongo._
import play.modules.reactivemongo.PlayBsonImplicits._
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._
import utils.Base62Code

object Application extends Controller with MongoController {

  val db = ReactiveMongoPlugin.db
  lazy val collection = db("links")

  val urlForm = Form(single("url" -> nonEmptyText))

  def index = Action {
    Ok(views.html.index(urlForm))
  }

  def create = Action { implicit request =>
    Async {
      urlForm.bindFromRequest.fold(
        errors => Future(BadRequest(views.html.index(errors))),
        urlForm => {
          Base62Code.generate(unique).flatMap { code =>
            val json = Json.obj(
              "url" -> urlForm,
              "code" -> code
            )
            collection.insert[JsValue](json).map { _ =>
              Ok(views.html.created(code))
            }.recover { case _ => InternalServerError }
          }
        }
      )
    }
  }

  private def unique(code: String) = {
    collection.find[JsValue](QueryBuilder().query(Json.obj("code" -> code))).headOption.map(_.isEmpty)
  }

  def redirect(code: String) = Action {
    Async {
      val qb = QueryBuilder().query(Json.obj("code" -> code))
      collection.find[JsValue](qb).headOption.map { link =>
        link match {
          case Some(link) => MovedPermanently((link \ "url").as[String])
          case None => NotFound
        }
      }
    }
  }

}