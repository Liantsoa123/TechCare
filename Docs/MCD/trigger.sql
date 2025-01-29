--Function to calculate the commission.
CREATE
OR REPLACE FUNCTION calculate_commission()
RETURNS TRIGGER AS $$
BEGIN

    IF
NEW.total >= 200000 THEN
        NEW.commission := NEW.total * 0.05;
ELSE
        NEW.commission := 0;
END IF;
RETURN NEW;
END;
$$
LANGUAGE plpgsql;
-- Then, create a trigger to call the function before inserting a repair.
CREATE TRIGGER set_commission_before_insert
    BEFORE INSERT
    ON repair
    FOR EACH ROW
    EXECUTE FUNCTION calculate_commission();



-- Create a trigger function to insert into historique_prix
CREATE
OR REPLACE FUNCTION insert_historique_after_component()
RETURNS TRIGGER AS $$
BEGIN
    -- Insert a new row into historique_prix with the current date, unit price, and component ID
INSERT INTO historique_prix (dateHisto, prix, component_id)
VALUES (CURRENT_DATE, NEW.unite_price, NEW.component_id);

RETURN NEW;
END;
$$
LANGUAGE plpgsql;
-- Create a trigger that fires after insert on component
CREATE TRIGGER trig_insert_historique_prix
    AFTER INSERT
    ON component
    FOR EACH ROW
    EXECUTE FUNCTION insert_historique_after_component();
