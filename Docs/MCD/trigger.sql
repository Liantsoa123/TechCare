--Function to calculate the commission.
CREATE
OR REPLACE FUNCTION calculate_commission()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if the total is greater than or equal to 200000
    IF
NEW.total >= 200000 THEN
        NEW.commission := NEW.total * 0.05; -- Set commission to 5% of total
ELSE
        NEW.commission := 0; -- Otherwise, set commission to 0
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

