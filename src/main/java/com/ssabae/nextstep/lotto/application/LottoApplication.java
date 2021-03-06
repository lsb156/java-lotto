package com.ssabae.nextstep.lotto.application;

import com.ssabae.nextstep.lotto.domain.Money;
import com.ssabae.nextstep.lotto.domain.WinningNumber;
import com.ssabae.nextstep.lotto.domain.lotto.ticketgenerator.AutoLottoTicketGenerator;
import com.ssabae.nextstep.lotto.domain.lotto.ticketgenerator.LottoTicketGenerator;
import com.ssabae.nextstep.lotto.domain.lotto.LottoTicketMachine;
import com.ssabae.nextstep.lotto.domain.lotto.LottoTickets;
import com.ssabae.nextstep.lotto.view.InputView;
import com.ssabae.nextstep.lotto.view.LottoPrinter;
import com.ssabae.nextstep.lotto.view.ResultView;
import java.util.List;

/**
 * @author : leesangbae
 * @project : java-lotto
 * @since : 2020-12-17
 */
public class LottoApplication {

    private final LottoTicketMachine lottoTicketMachine;
    private final ResultView resultView;

    public LottoApplication() {
        LottoTicketGenerator autoGenerator = new AutoLottoTicketGenerator();
        this.lottoTicketMachine = new LottoTicketMachine(autoGenerator);
        this.resultView = new ResultView();
    }

    public void launch() {
        Money money = InputView.buyMoneyInput();
        List<String> manualTicketStrings = InputView.manualTicketInput();
        LottoTickets lottoTickets = lottoTicketMachine.buy(money, manualTicketStrings);

        LottoPrinter.print(lottoTickets);

        WinningNumber winningNumber = InputView.winningNumberInput();
        LottoResultDto lottoResultDto = lottoTicketMachine.calculateYield(lottoTickets, winningNumber);

        resultView.report(lottoResultDto);
    }
}
