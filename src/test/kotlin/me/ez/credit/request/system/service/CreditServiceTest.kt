package me.ez.credit.request.system.service

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import me.ez.credit.request.system.entity.Address
import me.ez.credit.request.system.entity.Credit
import me.ez.credit.request.system.entity.Customer
import me.ez.credit.request.system.exception.BusinessException
import me.ez.credit.request.system.repository.CreditRepository
import me.ez.credit.request.system.service.impl.CreditService
import me.ez.credit.request.system.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var creditRepository: CreditRepository
    @MockK lateinit var customerService: CustomerService
    @InjectMockKs lateinit var creditService: CreditService

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should create a credit`() {
        // given
        val fakeCredit: Credit = buildCredit()
        val fakeCustomerId: Long = 1L
        every { customerService.findById(fakeCustomerId) } returns fakeCredit.customer!!
        every { creditRepository.save(any()) } returns fakeCredit

        // when
        val actual: Credit = creditService.save(fakeCredit)

        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }

    @Test
    fun `should find all the credits by customer id`() {
        // given
        val fakeCustomerId: Long = 1L
        val expectedCredits: List<Credit> = listOf(buildCredit(), buildCredit(), buildCredit())
        every { creditRepository.findAllByCustomerId(fakeCustomerId) } returns expectedCredits

        // when
        val actual: List<Credit> = creditService.findAllByCostumerId(fakeCustomerId)

        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isNotEmpty
        Assertions.assertThat(actual).isSameAs(expectedCredits)
        verify(exactly = 1) { creditRepository.findAllByCustomerId(fakeCustomerId) }
    }

    @Test
    fun `should find by credit code`() {
        // given
        val fakeCustomerId: Long = 1L
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(customer = Customer(id = fakeCustomerId))
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit

        // when
        val actual: Credit = creditService.findByCreditCode(fakeCustomerId, fakeCreditCode)

        // then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.findByCreditCode(fakeCreditCode) }
    }

    @Test
    fun `should throw BusinessException for invalid credit code`() {
        //given
        val fakeCustomerId: Long = 1L
        val invalidCreditCode: UUID = UUID.randomUUID()
        every { creditRepository?.findByCreditCode(invalidCreditCode) } returns null

        //when
        //then
        Assertions.assertThatThrownBy { creditService.findByCreditCode(fakeCustomerId, invalidCreditCode) }
            .isInstanceOf(BusinessException::class.java)
            .hasMessage("Creditcode $invalidCreditCode not found")
        verify(exactly = 1) { creditRepository.findByCreditCode(invalidCreditCode) }
    }

    @Test
    fun `should throw IllegalArgumentException for different customer ID`() {
        //given
        val fakeCustomerId: Long = 1L
        val fakeCreditCode: UUID = UUID.randomUUID()
        val fakeCredit: Credit = buildCredit(customer = Customer(id = 2L))
        every { creditRepository.findByCreditCode(fakeCreditCode) } returns fakeCredit

        //when
        //then
        Assertions.assertThatThrownBy { creditService.findByCreditCode(fakeCustomerId, fakeCreditCode) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("Contact admin")
        verify { creditRepository.findByCreditCode(fakeCreditCode) }
    }

       private fun buildCustomer(
        firstName: String = "Gabriel",
        lastName: String = "Bessi",
        cpf: String = "28475934625",
        email: String = "Bessi.Gabriel@gmail.com",
        password: String = "12345",
        zipCode: String = "54321",
        street: String = "Rua ez",
        income: BigDecimal = BigDecimal.valueOf(5000.0),
        id: Long = 8L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )


    companion object {
        private fun buildCredit(
            creditCode: UUID = UUID.randomUUID(),
            creditValue: BigDecimal = BigDecimal.valueOf(1000.0),
            dayFirstInstallment: LocalDate = LocalDate.of(2023, 12, 26),
            numberOfInstallments: Int = 8,
            customer: Customer = CustomerServiceTest.buildCustomer()
        ) = Credit(
            creditCode = creditCode,
            creditValue = creditValue,
            dayFirstInstallment = dayFirstInstallment,
            numberOfInstallments = numberOfInstallments,
            customer = customer
        )
    }
}
