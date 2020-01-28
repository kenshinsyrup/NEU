# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util


class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return [s, s, w, s, w, w, s, w]


def searchByGivenContainer(problem, container):
    # info means: (state, action, cost), its a tuple contains node's state and its action and action cost.
    # path means: [info], it contains many info which is from root node to current node.
    # container means: container(path), it contains paths during search. Container is kind of interface that has push,
    # pop, isEmpty method.

    start_state = problem.getStartState()
    # This is the path to start node. It only contains one node info (start_state, action, cost),
    # we could use random action string to mark its action, here use "Stop. And we mark its cost as 0.
    start_path = [(start_state, "Stop", 0)]

    # Push path into container. If container is Stack, this will put it at the tail;
    # if container is Queue, this also put it at the tail.
    container.push(start_path)

    # Initialise the Counter to record the visited nodes. Kind of like a Set. Whenever we start to process a node in
    # the Search part(while loop part below), we count the node to 1 means already processed.
    counter = util.Counter()

    ret = []
    # Loop to process all elements in container.
    while not container.isEmpty():
        # Pop out the top element from the container. If container is a queue, top is head;
        # if container is stack, top is tail.
        cur_path = container.pop()

        # The current state is the first element in the last tuple of the path.
        # For example, a path: [(root_state, "Stop", 0), (state_n, "North", 1)], its last info
        # is (state_n, "North", 1), so (state_n, "North", 1)[0] is state_n.
        cur_path_last_info = cur_path[-1]
        cur_state = cur_path_last_info[0]

        # If the current state is the goal state, build the result by current path,
        # what we need is the whole actions in this path.
        if problem.isGoalState(cur_state):
            for info in cur_path[1:]:
                ret.append(info[1])
            return ret

        # If the current state counter is 0, means we have not processed it, we need process it.
        if counter[cur_state] == 0:
            # Counter current state to 1 means we have processed it.
            counter[cur_state] = 1

            # Get all successors' info of current state. A successor is same as what we said "info" above,
            # has this format (state, action, cost)
            successors = problem.getSuccessors(cur_state)
            for successor in successors:
                # If the successor's state has not been processed, build path for it and push path to container.
                # If container is queue, this push path to tail; if container is stack, this also push path to tail.
                if counter[successor[0]] == 0:
                    successor_path = cur_path[:]
                    successor_path.append(successor)
                    container.push(successor_path)

    return ret


def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    # util.raiseNotDefined()

    # Initialize an empty Stack
    stack = util.Stack()
    # DFS is a search with Stack
    return searchByGivenContainer(problem, stack)


def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    # util.raiseNotDefined()

    # Initialize an empty Queue
    queue = util.Queue()
    # BFS is a search with Queue
    return searchByGivenContainer(problem, queue)


def pathCost(problem, path):
    actions = []
    for info in path[1:]:
        action = info[1]
        actions.append(action)
    return problem.getCostOfActions(actions)


def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    "*** YOUR CODE HERE ***"
    # util.raiseNotDefined()

    # A lambda function to calculate given path total cost which is all action cost in the path.
    # Here we use lambda function because the priority queue need a function with only one parameter but we need
    # the problem variable to get the cost from the path parameter
    path_cost = lambda path: problem.getCostOfActions([info[1] for info in path][1:])  # info is (state, action, cost)
    # Initialize an empty PriorityQueue
    pq = util.PriorityQueueWithFunction(path_cost)
    # UCS is a search with PriorityQueue which sorts elements by path_cost
    return searchByGivenContainer(problem, pq)


def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0


def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    # util.raiseNotDefined()

    # A lambda function to calculate given path total cost which is all action cost in the path plus heuristic cost.
    # Here we use lambda function because the priority queue need a function with only one parameter but we need
    # the problem variable to get the cost from the path parameter
    path_cost = lambda path: problem.getCostOfActions([info[1] for info in path][1:]) + heuristic(path[-1][0], problem)
    # Initialize an empty PriorityQueue
    pq = util.PriorityQueueWithFunction(path_cost)
    # A* is a search with PriorityQueue which sorts elements by path_cost
    return searchByGivenContainer(problem, pq)


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
