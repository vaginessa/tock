package ai.tock.bot.orchestration.bot.primary

import ai.tock.bot.definition.StoryDefinition
import ai.tock.bot.engine.BotBus
import ai.tock.bot.orchestration.shared.AvailableOrchestrationResponse

open class DefaultPrimaryBotOrchestrationEventHandler(
    private val comebackStory: StoryDefinition
) : PrimaryBotOrchestrationEventHandler {
    override fun onStarOrchestration(bus: BotBus, orchestrationResponse: AvailableOrchestrationResponse) {
        bus.send("To answer you, I hand over to my colleague {0} !", orchestrationResponse.targetBot.botLabel)
    }

    override fun onStopOrchestration(bus: BotBus, orchestration: Orchestration): ComeBackFromSecondary {
        bus.send("Your conversation with {0} is now over.", orchestration.targetBot.botLabel)
        bus.handleAndSwitchStory(comebackStory)

        return ComeBackFromSecondary.DO_NOTHING_MORE
    }

    override fun onNoOrchestration(bus: BotBus, orchestration: Orchestration): ComeBackFromSecondary {
        bus.send("Your conversation with {0} is now over.", orchestration.targetBot.botLabel)

        return ComeBackFromSecondary.EXECUTE_INITIAL_STORY
    }

    override fun onTakeBackOrchestration(bus: BotBus, orchestration: Orchestration): ComeBackFromSecondary {
        bus.end("Your conversation with {0} is now over.", orchestration.targetBot.botLabel)

        return ComeBackFromSecondary.DO_NOTHING_MORE
    }
}
