package bisq.gradle.regtest.bitcoind.tasks

import bisq.gradle.regtest.Network
import bisq.gradle.regtest.bitcoind.BitcoindRpcClient
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

abstract class BitcoindStopTask : DefaultTask() {
    @get:Input
    abstract val port: Property<Int>

    init {
        port.convention(StartBitcoinQtTask.DEFAULT_BITCOIND_RPC_PORT)
    }

    @TaskAction
    fun doRpcCall() {
        if (Network.isPortFree(port.get())) {
            throw StopExecutionException("bitcoind is not running.")
        }

        BitcoindRpcClient.daemonRpcCall(
            listOf("stop")
        )
    }
}