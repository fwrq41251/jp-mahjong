package com.winry

import java.util

import scala.collection.mutable.ListBuffer

/**
  * Created by User on 10/28/2016.
  */
object LongestAbsoluteFilePath {

  class Node(val name: String) {

    val nodeType: NodeType.Value = getNodeType(name)

    val subNodes = new ListBuffer[Node]

    def addNode(node: Node) = subNodes += node

    def addNodes(nodes: List[Node]) = subNodes.appendAll(nodes)

  }

  object NodeType extends Enumeration {
    val File, Dir = Value
  }

  def clearMap(nodeMap: util.TreeMap[Int, List[Node]], nodeDepth: Int) = {

  }

  def parse(input: String): Option[Node] = {
    val strs = input.split("\n")
    if (strs.isEmpty)
      Option.empty
    else {
      val headName = strs.head
      val root = new Node(headName)
      val tail = strs.tail
      if (tail.isEmpty) Option(root)
      else {
        //depth -> node
        val nodeMap = new util.TreeMap[Int, List[Node]]()(implicitly[Ordering[Int]].reverse)
        var depth = 0
        for (nodeStr <- tail) {
          val rawNode = getRawNode(nodeStr)
          val nodeDepth = rawNode._1
          val name = rawNode._2
          if (nodeDepth > depth) {
            depth = nodeDepth
          } else if (nodeDepth < depth) {
            depth = nodeDepth
            clearMap(nodeMap, nodeDepth)
          }
          nodeMap.put(nodeDepth, nodeMap.getOrDefault(nodeDepth, List[Node]()) :+ new Node(name))
        }
        nodeMap.get(1).foreach(_ => root.addNode(_))
        Option(root)
      }
    }
  }

  /**
    *
    * @param nodeStr
    * @return depth -> nodeName
    */
  def getRawNode(nodeStr: String): (Int, String) = {
    var tabCount = 0
    var tail = nodeStr
    while (tail.contains("\t")) {
      tabCount += 1
      val tabIndex = nodeStr.indexOf("\t")
      tail = nodeStr.substring(tabIndex)
    }
    tabCount -> tail
  }

  def getLongestLength(input: String): Int = {
    parse(input) match {
      case Some(node) => 2
      case None => 0
    }
  }

  def getNodeType(name: String): NodeType.Value = {
    if (name.contains('.')) NodeType.File else NodeType.Dir
  }

  def main(args: Array[String]): Unit = {
    println("dir\n\tsubdir1\n\t\tsubsubdir1\n\t\t\tfile1.ext\n\tsubdir2\n\tsubdir3\n\t\tsubsubdir2\n\t\t\tfile2.ext")
    val names = List("2", "3")
  }

}




