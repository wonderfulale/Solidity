package test;
import java.math.BigInteger;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.exceptions.ClientException;
import org.fisco.bcos.sdk.client.protocol.response.BcosBlock;
import org.fisco.bcos.sdk.client.protocol.response.BcosBlockHeader;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransaction;
import org.fisco.bcos.sdk.client.protocol.response.BcosTransactionReceiptsDecoder;
import org.fisco.bcos.sdk.client.protocol.response.BlockHash;
import org.fisco.bcos.sdk.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.client.protocol.response.ConsensusStatus;
import org.fisco.bcos.sdk.client.protocol.response.GroupList;
import org.fisco.bcos.sdk.client.protocol.response.Peers;
import org.fisco.bcos.sdk.client.protocol.response.PendingTransactions;
import org.fisco.bcos.sdk.client.protocol.response.PendingTxSize;
import org.fisco.bcos.sdk.client.protocol.response.SealerList;
import org.fisco.bcos.sdk.client.protocol.response.SyncStatus;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.contract.HelloWorld;
import org.fisco.bcos.sdk.model.ConstantConfig;
import org.fisco.bcos.sdk.model.NodeVersion;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.service.GroupManagerService;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.sdk.utils.Numeric;
import org.junit.Assert;
import org.junit.Test;

public class SolidityTest {
        // 获取配置文件路径
        public final String configFile = SolidityTest.class.getClassLoader().getResource("config-example.toml").getPath();
        public void testClient() throws ConfigException {
            // 初始化BcosSDK
            SolidityTest sdk =  SolidityTest.build(configFile);
            // 为群组1初始化client
            Client client = sdk.getClient(Integer.valueOf(1));

            // 获取群组1的块高
            BlockNumber blockNumber = client.getBlockNumber();

            // 向群组1部署HelloWorld合约
            CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
            HelloWorld helloWorld = HelloWorld.deploy(client, cryptoKeyPair);

            // 调用HelloWorld合约的get接口
            String getValue = helloWorld.get();

            // 调用HelloWorld合约的set接口
            TransactionReceipt receipt = helloWorld.set("Hello, fisco");
        }
}

