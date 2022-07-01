import { Button, Card, CardContent, Container, FormHelperText, Grid, TextField, Typography } from "@material-ui/core";
import CurrencyTextField from "@unicef/material-ui-currency-textfield/dist/CurrencyTextField";
import { useEffect, useState } from "react";
import ReactInputMask from "react-input-mask";

export default function AgendamentoTransferencia() {
    const [contaOrigem, setContaOrigem] = useState("");
    const [contaDestino, setContaDestino] = useState("");
    const [valor, setValor] = useState("");
    const [dataTransferencia, setDataTransferencia] = useState("");
    const [formErrors, setFormErrors] = useState({});
    const [isSubmit, setIsSubmit] = useState(false);

    const getFormValues = () => {
        return { contaOrigem, contaDestino, valor, dataTransferencia };
    }

    const handleClick = (e) => {
        e.preventDefault();
        setFormErrors(validateForm(getFormValues()));
        console.log("formErrors " + JSON.stringify(formErrors));
        setIsSubmit(true);
    }

    const validateForm = (values) => {
        const errors = {};
        if (!values.contaOrigem) {
            errors.contaOrigem = 'Conta de origem é obrigatória';
        }
        if (!values.contaDestino) {
            errors.contaDestino = 'Conta de destino é obrigatória';
        }
        if (!values.valor) {
            errors.valor = 'Valor é obrigatório';
        }
        if (!values.dataTransferencia) {
            errors.dataTransferencia = 'Data de transferencia é obrigatória';
        }
        return errors;
    }

    useEffect(() => {
        if (Object.keys(formErrors).length === 0 && isSubmit) {
            console.log("Let's submit: " + JSON.stringify(getFormValues()));
            setIsSubmit(false);
            // alert("Transferencia agendada com sucesso!");
        }
    }, [formErrors]);

    const styles = {
        card: {
            marginTop: "20px",
            padding: "20px"
        }
    }
    return (
        <Container>
            <Card style={styles.card}>
                <Typography variant="h6" gutterBottom>Agendamento de Transferência</Typography>

                <CardContent>
                    <Grid container spacing={1}>
                        <Grid xs={12} sm={6} md={3} item>
                            <ReactInputMask
                                mask="999999"
                                value={contaOrigem}
                                disabled={false}
                                maskChar=""
                                onChange={(e) => setContaOrigem(e.target.value)}
                                required
                            >
                                {() => <TextField label="Conta Origem" variant="outlined" InputLabelProps={{
                                    shrink: true,
                                }} fullWidth error={formErrors.contaOrigem} />}
                            </ReactInputMask>
                            <FormHelperText error>{formErrors.contaOrigem}</FormHelperText>
                        </Grid>
                        <Grid xs={12} sm={6} md={3} item>
                            <ReactInputMask
                                mask="999999"
                                value={contaDestino}
                                disabled={false}
                                maskChar=""
                                onChange={(e) => setContaDestino(e.target.value)}
                            >
                                {() => <TextField label="Conta Destino" variant="outlined" InputLabelProps={{
                                    shrink: true,
                                }} fullWidth error={formErrors.contaDestino} />}
                            </ReactInputMask>
                            <FormHelperText error>{formErrors.contaDestino}</FormHelperText>
                        </Grid>
                        <Grid xs={12} sm={6} md={3} item>
                            <TextField label="Data da Transferência" variant="outlined" type="date" InputLabelProps={{
                                shrink: true,
                            }} value={dataTransferencia} onChange={(event) => setDataTransferencia(event.target.value)}
                                fullWidth error={formErrors.dataTransferencia}></TextField>
                            <FormHelperText error>{formErrors.dataTransferencia}</FormHelperText>
                        </Grid>
                        <Grid xs={12} sm={6} md={3} item>
                            <CurrencyTextField
                                label="Valor"
                                variant="outlined"
                                value={valor}
                                currencySymbol="R$ "
                                minimumValue="0"
                                precision="2"
                                outputFormat="string"
                                decimalCharacter=","
                                digitGroupSeparator="."
                                onChange={(event, value) => setValor(value)}
                                fullWidth error={formErrors.valor}
                            />
                            <FormHelperText error>{formErrors.valor}</FormHelperText>
                        </Grid>
                        <Grid>
                            <Button variant="contained" color="primary" onClick={handleClick}>Agendar</Button>
                        </Grid>
                    </Grid>
                </CardContent>

            </Card>

            <Card style={{ marginTop: "20px" }}>
                <CardContent>
                    <Typography variant="h6" gutterBottom>Agendamentos</Typography>
                    <p>{contaOrigem}</p>
                    <p>{contaDestino}</p>
                    <p>{dataTransferencia}</p>
                    <p>{valor}</p>
                </CardContent>
            </Card>
        </Container>
    )
}