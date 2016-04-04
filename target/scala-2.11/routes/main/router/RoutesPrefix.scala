
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Amna/Desktop/CITRIS/appcivist-mobilizing-groups/conf/routes
// @DATE:Mon Apr 04 16:29:28 PDT 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
