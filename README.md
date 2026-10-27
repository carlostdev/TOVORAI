# TOVOR-AI: Monte Carlo Tree Search fighting-game AI

An AI agent for a fighting-game AI competition, written in Java. On every frame it runs a time-boxed Monte Carlo Tree Search (MCTS) against the game's forward simulator, then picks its next action with UCB1.

## How it works

Each frame, `processing()` builds a fresh search tree from the current game state and searches for about 16 ms, roughly one frame's budget at 60 fps:

1. **Selection.** Walk down the tree using UCB1. Unvisited children are tried first.
2. **Expansion.** On a node's second visit, add one child per available action.
3. **Simulation.** Use the toolkit's built-in simulator to play 35 frames ahead: the candidate action for this agent against a random action for the opponent.
4. **Evaluation.** Score the rollout by the HP difference between the two characters.
5. **Back-propagation.** Push visit counts and results up to the root.
6. **Decision.** Pick the root child with the best UCB1 value and send it as the next command.

## Structure

| Path | What it is |
|---|---|
| `src/TOVOR.java` | The agent (`AIInterface` implementation), with `McNode` and `McTree` as inner classes |
| `AIToolKit.jar` | Competition-provided toolkit and simulator (not my code) |

## Context

Written for Fighting Game AI Competition (FTGAIC), 2019. It is a learning implementation of MCTS, not a tuned competitor.

## What I'd change today

<!-- Keep only the items you have verified yourself. -->
- **Integer division in UCB1.** `getResult() / getVisits()` divides two `int`s, so the average reward is truncated before it reaches the exploration term.
- **Back-propagation adds totals, not the rollout score.** Each parent adds its child's running total rather than the single rollout result, which over-weights deeper paths.
- **Evaluation sign.** The score is damage taken minus damage dealt, and UCB1 maximises it. Unless the toolkit's HP convention runs the other way, the sign should be flipped.
- **Opponent model.** The opponent is a single uniformly random action. `randomAction()` also never picks the last value in the `Action` enum.
- **No tree reuse.** The tree is rebuilt every frame instead of keeping the chosen subtree.
- **Repo hygiene.** `bin/`, `.DS_Store` and IDE files are committed, and there are no build instructions.
