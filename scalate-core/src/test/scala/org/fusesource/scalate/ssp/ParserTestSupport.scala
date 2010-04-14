package org.fusesource.scalate.ssp

import _root_.org.fusesource.scalate.FunSuiteSupport
import collection.mutable.HashMap

/**
 * @version $Revision: 1.1 $
 */
abstract class ParserTestSupport  extends FunSuiteSupport {
  var logging = false

  implicit def stringToText(x:String) = Text(x)

  def countTypes(lines: List[PageFragment]): HashMap[Class[_], Int] = {
    val map = new HashMap[Class[_], Int]
    for (line <- lines) {
      val key = line.getClass
      map(key) = map.getOrElse(key, 0) + 1
    }
    map
  }


  def assertAttribute(lines: List[PageFragment], expectedParam: AttributeFragment) = {
    val attribute = lines.find {
      case d: AttributeFragment => true
      case _ => false
    }
    expect(Some(expectedParam)) {attribute}

    lines
  }

  def assertValid(text: String): List[PageFragment] = {
    log("Parsing...")
    log(text)
    log("")

    val lines = (new SspParser).getPageFragments(text)
    for (line <- lines) {
      log(line)
    }
    log("")
    lines
  }


    def assertType(anyRef: AnyRef, expectedClass: Class[_]): Unit = {
      assert(anyRef != null, "expected instance of " + expectedClass.getName)
      expect(expectedClass) {anyRef.getClass}
    }

  def log(value: AnyRef) {
    if (logging) {
      println(value)
    }
  }
}
